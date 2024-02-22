package com.onlineauction.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.entity.Category;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.CategoryInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createOrUpdateCategoryDetails")
	public ResponseEntity<ResponseStatus<Object>> createCategory(@RequestBody Category category) throws CustomException{
		
		Category savedCategory = categoryService.createOrUpdateCategory(category);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedCategory);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	

	@GetMapping("/getCategorys")
	public ResponseEntity<ResponseStatus<Object>> getAllCategorys() throws CustomException{
		
		List<Category> categorys = categoryService.getAllCategorys();
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",categorys);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
	}

	
}
