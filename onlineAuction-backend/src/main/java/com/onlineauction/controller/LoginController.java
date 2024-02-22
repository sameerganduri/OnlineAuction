package com.onlineauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public ResponseEntity<ResponseStatus<Object>> loginUser(@RequestParam("userName") String userName) throws CustomException {
		
		
		ResponseStatus<Object> response = loginService.login(userName);
		
		
		return new ResponseEntity<ResponseStatus<Object>>(response,HttpStatus.OK);
	}

}
