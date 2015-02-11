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
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Comment;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.CommentService;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/{id}")
	public Map<String, Object> comment(@PathVariable("id") int id) {
		Comment comment = commentService.findCommentByID(id);
		Map<String, Object> ret = new HashMap<String, Object>();
		if(comment == null) {
			ret.put("status", Property.ERROR);
		}else {
			ret.put("status", Property.SUCCESS);
			ret.put("comment", comment);
		}
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)	
	public Map<String, String> createComment(@RequestParam("comment_object_type") int comment_object_type,
											 @RequestParam("comment_object_id") int comment_object_id,
											 @RequestParam("comment_content") String comment_content,
											 @RequestParam("comment_parent") int comment_parent,
											 HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, String> ret = commentService.newComment(comment_object_type, 
															comment_object_id, 
															user.getId(), 
															user.getUser_email(), 
															comment_content, 
															comment_parent,
															comment_parent!=0?userService.findById(comment_parent).getUser_email():null);
		return ret;
	}
	
	@RequestMapping(value="/{type}/{id}")
	public ModelAndView getComments(@PathVariable("type") String type, @PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("comment/index");
		mav.addObject("comments", commentService.getComments(type, id));
		return mav;
	}
}
