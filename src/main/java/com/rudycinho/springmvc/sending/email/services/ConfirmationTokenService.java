package com.rudycinho.springmvc.sending.email.services;

import org.springframework.stereotype.Service;

import com.rudycinho.springmvc.sending.email.models.ConfirmationToken;
import com.rudycinho.springmvc.sending.email.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	}

	public void create(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}
}
