package com.onlineauction.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.onlineauction.exception.CustomException;

@Component
public class Mapper {
	
	public <T> T convert(Object srcObj,Class<T> targetClass) throws CustomException {
		T response = null;
		
	ModelMapper modelMapper = new ModelMapper();		
	
	try {
		response=modelMapper.map(srcObj, targetClass);
	}catch(Exception e){
		e.printStackTrace();
		throw new CustomException(e.getMessage(),HttpStatus.BAD_REQUEST.value());
	}
		
		return response;
	}

	
	
	public <S,T> List<T> convertToList(List<S> srcList,Class<T> targetClass) throws CustomException{
		List<T> response = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		try {
			srcList.stream().forEach(source->response.add(modelMapper.map(source, targetClass)));
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),HttpStatus.BAD_REQUEST.value());
		}
		
		
		return response;
	}
}
