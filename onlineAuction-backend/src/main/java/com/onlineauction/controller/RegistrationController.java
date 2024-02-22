package com.onlineauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.RegistrationInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.RegistrationService;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<ResponseStatus<Object>> createProduct(@RequestBody RegistrationInfo registrationInfo)
			throws CustomException {
		ResponseStatus<Object> resposne = registrationService.register(registrationInfo);
		return new ResponseEntity<ResponseStatus<Object>>(resposne, HttpStatus.OK);

	}
}
