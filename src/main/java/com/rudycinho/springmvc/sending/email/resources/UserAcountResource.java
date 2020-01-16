package com.rudycinho.springmvc.sending.email.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rudycinho.springmvc.sending.email.models.ConfirmationToken;
import com.rudycinho.springmvc.sending.email.models.User;
import com.rudycinho.springmvc.sending.email.services.ConfirmationTokenService;
import com.rudycinho.springmvc.sending.email.services.EmailSenderService;
import com.rudycinho.springmvc.sending.email.services.UserService;
import com.rudycinho.springmvc.sending.email.vo.UserVO;

@RestController
public class UserAcountResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserVO userVO) { 
		ResponseEntity<?> response;
		User existingUser = userService.findByEmailId(userVO.getEmailId());
		if(existingUser != null){
			Map<String,String> errors = new HashMap<>();
			errors.put("Error","This email already exists!");
			response = new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}else {
			User user = new User();
			user.setFirstName(userVO.getFirstName());
			user.setLastName(userVO.getLastName());
			user.setEmailId(userVO.getEmailId());
			user.setPassword(userVO.getPassword());
			user.setEnabled(userVO.isEnabled());
			userService.create(user);
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenService.create(confirmationToken);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmailId());
			simpleMailMessage.setSubject("Complete Registration!");
			simpleMailMessage.setFrom("n.times000@gmail.com");
			simpleMailMessage.setText("To confirm your account, please click here: " 
			+ "localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken() );
			emailSenderService.sendEmail(simpleMailMessage);
			response =  new ResponseEntity<>(null,HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken){
		System.out.println("PUNTO 1");
		ResponseEntity<?> response;
		System.out.println("PUNTO 2");
		System.out.println(">" + confirmationToken);
		ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);
		System.out.println("PUNTO 3");
		if(token!=null) {
			System.out.println("PUNTO 4A");
			System.out.println(token.getConfirmationToken());
			User user = userService.findByEmailId(token.getUser().getEmailId());
			System.out.println("PUNTO 5A");
			user.setEnabled(true);
			System.out.println("PUNTO 6A");
			userService.update(user);
			System.out.println("PUNTO 7A");
			response =  new ResponseEntity<>(null,HttpStatus.OK);
		}else {
			System.out.println("PUNTO 4B");
			Map<String,String> errors = new HashMap<>();
			errors.put("Error","This email already exists!");
			System.out.println("PUNTO 5B");
			response = new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		}
		return response;
	}
}








