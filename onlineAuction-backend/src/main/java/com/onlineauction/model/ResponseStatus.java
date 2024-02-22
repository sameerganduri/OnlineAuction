package com.onlineauction.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ResponseStatus<T> {

	int code;
	String status;
	T data;
	
}
