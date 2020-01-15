package com.rudycinho.springmvc.sending.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudycinho.springmvc.sending.email.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String>{
	ConfirmationToken findByConfirmationToken(String confirmationToken);	
}
