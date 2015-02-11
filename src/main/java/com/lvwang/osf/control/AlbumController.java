package com.lvwang.osf.control;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.AlbumService;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	@Qualifier("albumService") 
	private AlbumService albumService;
	
	@RequestMapping("/{id}")
	public String album() {
		return "album/index";
	}
	
	/*
	 * 相册上传页面
	 */
	@RequestMapping(value="/{album_id}/upload", method=RequestMethod.GET)
	public String albumUploadPage(@PathVariable("album_id") int id) {
		return "album/upload";
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
	@RequestMapping("/create") 
	public Map<String, Object> createAlbum(@RequestParam("albumdesc") String desc, @RequestParam("photos") String photos) {
		return null;
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
		
		//创建临时相册
		Map<String, Object> albumMap = albumService.newAlbum(user.getId(), null, null);
		if(!Property.SUCCESS_ALBUM_CREATE.equals(albumMap.get("status")) ) {
			map.put("status", albumMap.get("status"));
			return map;
		}
		int album_id = ((Album)albumMap.get("album")).getId();
		//上传图片
		Map<String, Object> photoMap = albumService.newPhoto(album_id, img, null);
		map.put("status", photoMap.get("status"));	
		map.put("photo", photoMap.get("photo"));
		return map;
	}
	
}
