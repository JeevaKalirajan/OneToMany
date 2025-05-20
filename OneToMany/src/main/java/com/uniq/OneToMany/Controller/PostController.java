package com.uniq.OneToMany.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uniq.OneToMany.Entity.Post;
import com.uniq.OneToMany.Entity.User;
import com.uniq.OneToMany.service.PostService;

@RestController
public class PostController {

	@Autowired
	PostService service;
	
	@PostMapping("/savePost/{id}")
	public Post save(@PathVariable int id ,@RequestBody Post post) {
		return service.savePost(id, post);
	}
	
	@GetMapping("/posts/{uid}")
	public List<Post> byUserId(@PathVariable int uid){
		return service.userId(uid);
	}
	
	@GetMapping("/findByLocation/{location}")
	public ResponseEntity<List<User>> findByLocation(@PathVariable String location) {
		List<User> u = service.findByLocation(location);
		return ResponseEntity.ok(u);
	}
	
	@PostMapping("/updatePost")
	public ResponseEntity<Post> update(int uid , int pid , Post post){
		post.setId(pid);
		Post p = service.update(uid, pid, post);
		return ResponseEntity.ok(p);
	}
}
