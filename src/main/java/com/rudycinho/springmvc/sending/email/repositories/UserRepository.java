package com.rudycinho.springmvc.sending.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudycinho.springmvc.sending.email.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmailId(String emailId);
}
