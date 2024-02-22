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

import com.onlineauction.entity.Product;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ProductInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/createOrUpdateProductDetails")
	public ResponseEntity<ResponseStatus<Object>> createProduct(@RequestBody Product product) throws CustomException{
		
		Product savedProduct = productService.createOrUpdateProduct(product);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedProduct);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	

	@GetMapping("/getProducts")
	public ResponseEntity<ResponseStatus<Object>> getAllProducts() throws CustomException{
		
		List<Product> products = productService.getAllProducts();
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",products);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
	}

	
	@GetMapping("/getProductsById")
	public ResponseEntity<Object> getProductById(@RequestParam int productId) throws CustomException {
		Product savedProduct;
			savedProduct = productService.getProductById(productId);
		
		
		if(Objects.isNull(savedProduct)) {
			throw new CustomException("Product does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		System.out.println(savedProduct.toString());
		return new ResponseEntity<>(savedProduct,HttpStatus.OK);
	}
	
	
	

	@GetMapping("/getProductsByCategoryId")
	public List<ProductInfo> getProductByCategoryId(@RequestParam int categoryId) throws CustomException {
			List<ProductInfo> savedProduct;
			savedProduct = productService.getProductByCategoryId(categoryId);
		
		
		if(Objects.isNull(savedProduct)) {
			throw new CustomException("Product does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedProduct;
	}
	
	
	@GetMapping("/getProductsBySellerId")
	public List<ProductInfo> getProductBySellerId(@RequestParam int sellerId) throws CustomException {
			List<ProductInfo> savedProduct;
			savedProduct = productService.getProductBySellerId(sellerId);
		
		
		if(Objects.isNull(savedProduct)) {
			throw new CustomException("Product does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedProduct;
	}
	
	
	
	
	
	@DeleteMapping("/deleteByProductId")
	public ResponseEntity<ResponseStatus<Object>> deleteByProductId(@RequestParam int productId) throws CustomException {
				ProductInfo deletedProduct;
				ResponseStatus<Object> responseObj;
		Boolean isDeleted =  productService.deleteByProductId(productId);
		if(isDeleted) {
			responseObj = new ResponseStatus<Object>(HttpStatus.NOT_FOUND.value(), "failure while deleting no entity exists", isDeleted);
			return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.NOT_FOUND);
		}
		
		responseObj= new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",isDeleted);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
		
	
	
	
	}
	
	@GetMapping("/search")
	public List<ProductInfo> getProductByCategoryId(@RequestParam String searchTerm) throws CustomException {
			List<ProductInfo> savedProduct;
			savedProduct = productService.searchProductByName(searchTerm);
		
		
		if(Objects.isNull(savedProduct)) {
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedProduct;
	}
	
	
	
	
}
