package com.onlineauction.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends Exception{
	
	

	/**
	 * 
	 */
	
	  
	 static final long serialVersionUID = 1L;
	String message;
	int status;

}
