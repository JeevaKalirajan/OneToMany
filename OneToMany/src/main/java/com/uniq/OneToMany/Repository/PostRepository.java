package com.uniq.OneToMany.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uniq.OneToMany.Entity.Post;
import com.uniq.OneToMany.Entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByuserId(int uid);
	
//	Post findByLocation(String location);
	
	@Query("SELECT DISTINCT p.user FROM Post p WHERE p.location = ?1")
	List<User> findUsersByLocation(String location);
	
//	@Query("select p.user from Post p where p.location = ?1")
//	User findByLocation(String location);
}
