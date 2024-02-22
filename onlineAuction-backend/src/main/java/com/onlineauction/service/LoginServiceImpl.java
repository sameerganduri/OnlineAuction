package com.onlineauction.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.User;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.model.UserInfo;
import com.onlineauction.repository.UserRepository;
import com.onlineauction.util.Mapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private Mapper mapper;

	@Autowired
	UserRepository userRepo;

	@Override
	public ResponseStatus<Object> login(String userName) throws CustomException {
		ResponseStatus<Object> response = null;

		User user = userRepo.findByUserName(userName);
		if (!Objects.isNull(user)) {
			if ("Y".equalsIgnoreCase(user.getStatus())) {
				response = new ResponseStatus<Object>(HttpStatus.OK.value(), "Success",
						mapper.convert(user, UserInfo.class));
			} else
				throw new CustomException("User Status Inactive", HttpStatus.NOT_ACCEPTABLE.value());
		} else {
			throw new CustomException("User Login Failed", HttpStatus.NOT_FOUND.value());
		}

		return response;
	}

}
