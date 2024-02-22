package com.onlineauction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onlineauction.entity.Product;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ProductInfo;


public interface ProductService {

	Product createOrUpdateProduct(Product product) throws CustomException;

	List<Product> getAllProducts() throws CustomException;

	Product getProductById(int productId) throws CustomException;

	List<ProductInfo> getProductByCategoryId(int categoryId) throws CustomException;

	Boolean deleteByProductId(int productId) throws CustomException;

	List<ProductInfo> searchProductByName(String searchTerm) throws CustomException;

	List<ProductInfo> getProductBySellerId(int sellerId)throws CustomException;

}
