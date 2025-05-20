package com.uniq.OneToMany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniq.OneToMany.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
