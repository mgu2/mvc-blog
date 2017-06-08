package blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blog.models.Post;
import blog.services.NotificationService;
import blog.services.PostService;

@Controller
public class PostsController {
        @Autowired
        private PostService postService;

        @Autowired
        private NotificationService notifyService;

        @RequestMapping("/posts/view/{id}")
        public String view(@PathVariable("id") Long id, Model model) {
            Post post = postService.findById(id);
            if (post == null) {
                notifyService.addErrorMessage("Cannot find post #" + id);
                return "redirect:/";
            }
            model.addAttribute("post", post);
            return "posts/view";
        }
        
        @RequestMapping("/posts/delete/{id}")
        public String delete(@PathVariable("id") Long id, Model model) {
        	Post post = postService.findById(id);
            if (post == null) {
                notifyService.addErrorMessage("Cannot find post #" + id);
                
            }
            else{
            	notifyService.addInfoMessage("Delete Successfully");
            	postService.deleteById(id);
            }
        		
            return "redirect:/";
        }
        
        @RequestMapping(value="/posts/create", method=RequestMethod.POST)
        public String create(
        		@RequestBody @Valid Post post, 
        		Model model){
        	
        	if(post==null)
        		notifyService.addErrorMessage("Cannot create new post");
        	else{
        		postService.create(post);
        		notifyService.addInfoMessage("Post created successfully");
        	}
        	
        	return "redirect:/";
        }
        
        @RequestMapping(value="/post/edit/{id}", method=RequestMethod.PATCH)
        public String edit(
        		@RequestParam(required = true) Long id,
        		@RequestBody @Valid Post post,
        		Model model){
        	
        	if(post==null||postService.findById(id)==null)
        		notifyService.addErrorMessage("Cannot create new post");
        	
        	postService.deleteById(id);
        	postService.create(post);
        	
        	
        	return "redirect:/";
        }
        
        
}
