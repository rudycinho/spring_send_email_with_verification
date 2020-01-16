package com.rudycinho.springmvc.sending.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudycinho.springmvc.sending.email.models.ConfirmationToken;
import com.rudycinho.springmvc.sending.email.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	}

	public void create(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}
}
