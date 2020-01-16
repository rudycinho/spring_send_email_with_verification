package com.rudycinho.springmvc.sending.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudycinho.springmvc.sending.email.models.User;
import com.rudycinho.springmvc.sending.email.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User findByEmailId(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	public void create(User user) {
		userRepository.save(user);
	}

	public void update(User user) {
		userRepository.save(user);
	}
}
