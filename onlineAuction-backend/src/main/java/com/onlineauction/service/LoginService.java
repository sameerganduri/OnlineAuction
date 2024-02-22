package com.onlineauction.service;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;

public interface LoginService {
 ResponseStatus<Object> login(String userName) throws CustomException;
}
