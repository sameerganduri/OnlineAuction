package com.onlineauction.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
	
		private String firstName;
	    private String lastName;
	    private String email;
	    private String password;
	    private String role;

}
