package com.lvwang.osf.control;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.AlbumService;
import com.lvwang.osf.service.EventService;
import com.lvwang.osf.service.FeedService;
import com.lvwang.osf.util.Dic;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/album")
public class AlbumController {

	
	@Autowired
	@Qualifier("albumService") 
	private AlbumService albumService;
	
	@Autowired
	@Qualifier("eventService")
	private EventService eventService;
	
	@Autowired
	@Qualifier("feedService")
	private FeedService feedService;
	
	
	@RequestMapping("/{id}/photos")
	public ModelAndView album(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		List<Photo> photos = albumService.getPhotosOfAlbum(id);
		mav.addObject("album_id", id);
		mav.addObject("photos", photos);
		
		User user = albumService.getAuthorOfALbum(id); 
		mav.addObject("u", user);
		
		mav.setViewName("album/index");
		mav.addObject("imgBaseUrl", "http://osfimgs.oss-cn-hangzhou.aliyuncs.com/");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	public Album getAlbumInfo(@PathVariable("id") int id) {
		return albumService.getAlbum(id);
	}
	
	/*
	 * 相册上传页面
	 * 指定album
	 */
	@RequestMapping(value="/{album_id}/upload", method=RequestMethod.GET)
	public String albumUploadPage(@PathVariable("album_id") int id) {
		return "album/upload";
	}
	
	
	/*
	 * 相册上传页面
	 * 未指定album
	 */
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public ModelAndView albumUploadPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = (User) session.getAttribute("user");
		int album_id = albumService.getToBeReleasedAlbum(user.getId());
		List<Photo> photos = albumService.getPhotosOfAlbum(album_id);
		session.setAttribute("album_id", album_id);
		mav.addObject("photos", photos);
		mav.setViewName("album/upload");
		return mav;
	}
	
	/*
	 * 上传图片到相册
	 */
	@ResponseBody
	@RequestMapping(value="/{album_id}/upload/photo",  method=RequestMethod.POST)
	public Map<String, Object> albumUpload(@PathVariable("album_id") int album_id, 
										   @RequestParam("uploader_input") MultipartFile img, 
										   HttpSession session) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(img.isEmpty()) {
			map.put("status", Property.ERROR_PHOTO_EMPTY);
			return map;
		}
		
		User user = (User) session.getAttribute("user");
		//检查相册是否属于用户
		if(!Property.SUCCESS_ALBUM_ALLOWED.equals(albumService.checkUserOfAlbum(album_id, user.getId()))) {
			map.put("status", Property.ERROR_ALBUM_PERMISSIONDENIED);
			return map;
		}
		//上传图片
		Map<String, Object> photoMap = albumService.newPhoto(album_id, img, null);
		map.put("status", photoMap.get("status"));
		map.put("photo", photoMap.get("photo"));
		
		return map;
	}
	
	/*
	 * 创建相册
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST) 
	public Map<String, Object> createAlbum(@RequestBody String params, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(params);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(params);
			//update album desc
			String album_desc = root.path("album_desc").getTextValue();
			String status = albumService.updateAlbumDesc((Integer)session.getAttribute("album_id"), album_desc);
			map.put("status", status);
			if(!Property.SUCCESS_ALBUM_UPDATE.equals(status)) 
				return map;
			
			JsonNode photos = root.path("photos");
			//update album cover
			if(photos.size() > 0) {
				status = albumService.updateAlbumCover(
						 (Integer)session.getAttribute("album_id"),
						 albumService.getKeyofPhoto(
								 	  Integer.parseInt(
								 	  photos.get(0).path("id").getTextValue())
								 	  )
				);
			}
			if(!Property.SUCCESS_ALBUM_UPDATE.equals(status)) 
				return map;
			
			//update photos , set desc
			List<Photo> photos2upd = new ArrayList<Photo>();
			for(int i=0; i<photos.size(); i++) {
				int photo_id = Integer.parseInt(photos.get(i).path("id").getTextValue());
				String photo_desc = photos.get(i).path("desc").getTextValue();
				Photo photo = new Photo();
				photo.setId(photo_id);
				photo.setDesc(photo_desc);
				photos2upd.add(photo);
				
				System.out.println("photo_id:"+photo_id+" desc:"+photo_desc);
			}
			status = albumService.updatePhotoDesc(photos2upd);
			map.put("status", status);
			
			//add event
			User user = (User)session.getAttribute("user");
			Album album = new Album();
			album.setId((Integer)session.getAttribute("album_id"));
			album.setUser_id(user.getId());
			album.setAlbum_desc(album_desc);
			album.setPhotos_count(photos2upd.size());
			album.setPhotos(photos2upd);
			int event_id = eventService.newEvent(Dic.OBJECT_TYPE_ALBUM, album);
			if(event_id !=0 ) {
				feedService.push(user.getId(), event_id);
			}
			map.put("album", album);
		} catch (JsonProcessingException e) {
			map.put("status", Property.ERROR_ALBUM_CREATE);
		} catch (IOException e) {
			map.put("status", Property.ERROR_ALBUM_CREATE);
		}
		return map;
	}
	
	
	
	/*
	 * 未指定相册
	 * 临时创建相册
	 */
	@ResponseBody
	@RequestMapping(value="/upload/photo", method=RequestMethod.POST)
	public Map<String, Object> uploadPhoto(@RequestParam("uploader_input") MultipartFile img,
										    HttpSession session) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(img.isEmpty()) {
			map.put("status", Property.ERROR_PHOTO_EMPTY);
			return map;
		}
		
		User user = (User) session.getAttribute("user");
		Integer album_id = (Integer)session.getAttribute("album_id");
		
		//创建临时相册
		if(album_id == null || album_id == 0) {
			Map<String, Object> albumMap = albumService.newAlbum(user.getId(), null, null, AlbumService.ALBUM_STAUS_TOBERELEASED,null);
			if(!Property.SUCCESS_ALBUM_CREATE.equals(albumMap.get("status")) ) {
				map.put("status", albumMap.get("status"));
				return map;
			}
			album_id = ((Album)albumMap.get("album")).getId();
			session.setAttribute("album_id", album_id);
		}
		
		//上传图片
		Map<String, Object> photoMap = albumService.newPhoto(album_id, img, null);
		map.put("status", photoMap.get("status"));	
		Photo photo = (Photo)photoMap.get("photo");		
		map.put("id", photo.getId());
		map.put("key", photo.getKey());
		return map;
	}
	
	/*
	 * post 中图片上传
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/upload/postphoto", method=RequestMethod.POST)
	public Map<String, Object> postPhotoUpload(@RequestParam("uploader_input") MultipartFile img,
										    HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(img.isEmpty()) {
			map.put("status", Property.ERROR_PHOTO_EMPTY);
			return map;
		}
		
		//upload photo
		map = albumService.uploadPhoto(img);
		//set post cover
		session.setAttribute("post_cover", map.get("link"));
		return map;
	}
	
}
