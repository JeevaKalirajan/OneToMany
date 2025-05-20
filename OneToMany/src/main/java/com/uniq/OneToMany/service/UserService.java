package com.uniq.OneToMany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniq.OneToMany.Entity.User;
import com.uniq.OneToMany.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public List<User> getUser(){
		return repository.findAll();
	}
	
	public Optional<User> getUser(int id) {
		return repository.findById(id);
	}
	
	public User update(int id , User u) {
		Optional<User> exist = repository.findById(id);
		
		if(exist.isPresent()) {
			User present = exist.get();
			present.setName(u.getName());
			repository.save(present);
		}
		return null;
	}
	
	
}
