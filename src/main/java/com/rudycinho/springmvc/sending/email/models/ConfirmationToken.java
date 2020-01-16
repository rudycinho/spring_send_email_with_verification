package com.rudycinho.springmvc.sending.email.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "confirmation_tokens")
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "confirmation_token")
	private String confirmationToken;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	public ConfirmationToken(User user) {
		this.user = user;
		createdDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
	}
	
	public ConfirmationToken() {}
}
