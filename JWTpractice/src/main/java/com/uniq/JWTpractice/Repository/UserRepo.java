package com.uniq.JWTpractice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniq.JWTpractice.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

}
