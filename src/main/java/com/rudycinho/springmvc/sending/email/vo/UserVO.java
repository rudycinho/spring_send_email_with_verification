package com.rudycinho.springmvc.sending.email.vo;

import lombok.Getter;

@Getter
public class UserVO {
	private String emailId;	
	private String password;
	private String firstName;
	private String lastName;
	private boolean isEnabled;
}
