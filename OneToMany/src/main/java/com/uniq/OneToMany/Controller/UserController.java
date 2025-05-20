package com.uniq.OneToMany.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniq.OneToMany.Entity.User;
import com.uniq.OneToMany.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		return service.getUser(id);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> update(@PathVariable int id ,@RequestParam User user){
		user.setId(id);
		User u = service.update(id, user);
		return ResponseEntity.ok(u);
	}

}
