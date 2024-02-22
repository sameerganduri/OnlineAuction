package com.onlineauction.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private Integer userId;
	private String userName;
	private String password;
	private String roles;
	String status;
	String firstName;
	String lastName;
}
