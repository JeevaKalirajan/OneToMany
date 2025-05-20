package com.uniq.OneToMany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniq.OneToMany.Entity.Post;
import com.uniq.OneToMany.Entity.User;
import com.uniq.OneToMany.Repository.PostRepository;
import com.uniq.OneToMany.Repository.UserRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Post savePost(int uid , Post post) {
		Optional<User> user = userRepository.findById(uid);
		
		if(user.isPresent()) {
			User us = user.get();
			us.getPost().add(post);
			post.setUser(us);
			return postRepository.save(post);
		}
		return null;
	}
	
	public List<Post> userId(int uid){
		return postRepository.findByuserId(uid);
	}
	
	public List<User> findByLocation(String location) {
		List<User> p = postRepository.findUsersByLocation(location);
		return p;
	}
	
	public Post update(int uid ,int pid , Post post) {
		Optional<User> user = userRepository.findById(uid);
		
		Optional<Post> exist = postRepository.findById(pid);
		
		if(exist.isPresent()) {
			Post p = exist.get();
			User u = user.get();
			p.setLocation(post.getLocation());
			p.setUser(u);
			postRepository.save(p);
		}
		return null;
		
	}
}
