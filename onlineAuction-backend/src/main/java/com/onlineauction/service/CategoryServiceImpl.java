package com.onlineauction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Category;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.CategoryRepository;
import com.onlineauction.repository.ProductRepository;
import com.onlineauction.util.Mapper;

@Service
public class CategoryServiceImpl  implements CategoryService{

private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Mapper mapper;
	
	
	
	@Override
	public Category createOrUpdateCategory(Category category) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateCategory");
		ResponseStatus<Object> response = null;
		Category categoryResponse = new Category();		
		
		if(Objects.isNull(category.getCategoryId())  ) {
			category.setCategoryId(0);
			
		}
		
		
		categoryResponse = categoryRepository.saveAndFlush(category);

		return categoryResponse;
	}

	@Override
	public List<Category> getAllCategorys() throws CustomException{
		logger.info("Entered get Categorys");
	
		List<Category> categoryList=new ArrayList<>();
		try {
		 categoryList = categoryRepository.findAll();
		} catch (Exception e) {
			throw new CustomException(e.getMessage(),HttpStatus.NOT_FOUND.value());
		}
		
		
		return categoryList;
	}

	
	
	
}
