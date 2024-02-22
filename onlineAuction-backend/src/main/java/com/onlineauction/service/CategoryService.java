package com.onlineauction.service;

import java.util.List;

import com.onlineauction.entity.Category;
import com.onlineauction.exception.CustomException;

public interface CategoryService {

	Category createOrUpdateCategory(Category category) throws CustomException;

	List<Category> getAllCategorys()throws CustomException;

}
