package com.onlineauction.service;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.RegistrationInfo;
import com.onlineauction.model.ResponseStatus;

public interface RegistrationService {

	ResponseStatus<Object> register(RegistrationInfo registrationInfo) throws CustomException;

	ResponseStatus<Object> updateUser(RegistrationInfo registrationInfo) throws CustomException;
}
