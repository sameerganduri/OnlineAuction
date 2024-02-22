package com.onlineauction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Product;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ProductInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.ProductRepository;
import com.onlineauction.util.Mapper;

@Service
public class ProductServiceImpl implements ProductService{

	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public Product createOrUpdateProduct(Product product) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateProduct");
		ResponseStatus<Object> response = null;
		Product productResponse = new Product();		
		logger.info(product.toString());
		if(Objects.isNull(product.getProductId())  ) {
			product.setProductId(0);
			
		}
		
		
		productResponse = productRepository.save(product);

		return productResponse;
	}

	@Override
	public List<Product> getAllProducts() throws CustomException{
		logger.info("Entered get Products");
	
		List<Product> productList=new ArrayList<>();
		try {
		 productList = productRepository.findAll();
		} catch (Exception e) {
			throw new CustomException(e.getMessage(),HttpStatus.NOT_FOUND.value());
		}
		
		
		return productList;
	}

	@Override
	public Product getProductById(int productId) throws CustomException {
		logger.info("Entered getProductById");
		Optional<Product> productOptional = productRepository.findById(productId);
		logger.info(productOptional.get().toString());
		if(!productOptional.isPresent())
		{
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
		}
		Product product = productOptional.get();
	//	ProductInfo productInfo = mapper.convert(product, ProductInfo.class);
		return product;
	}
	
	@Override
	public List<ProductInfo> getProductByCategoryId(int categoryId) throws CustomException {
		logger.info("Entered getProductById");
		List<Product> product = productRepository.findByCategoryId(categoryId);
		//logger.info(productOptional.get().toString());
//		if(!productOptional.isPresent())
//		{
//			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
//		}
		//List<Product> product = productOptional.get();
		List<ProductInfo> productInfo = mapper.convertToList(product, ProductInfo.class);
		return productInfo;
	}

	@Override
	public Boolean deleteByProductId(int productId) throws CustomException {
		
		if(productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return true;
		}
		return false;
	}
	
	
	@Override
	public List<ProductInfo> searchProductByName(String searchTerm) throws CustomException {
		logger.info("Entered serchProductByName");
		List<Product> products = productRepository.searchProductsByProductNameOrDescription(searchTerm);
		List<ProductInfo> productInfo = mapper.convertToList(products, ProductInfo.class);
		return productInfo;
	}

	@Override
	public List<ProductInfo> getProductBySellerId(int sellerId) throws CustomException {
		logger.info("Entered getProductBySellerId");
		List<Product> product = productRepository.findBySellerId(sellerId);
		List<ProductInfo> productInfo = mapper.convertToList(product, ProductInfo.class);
		return productInfo;
	}
	

	
}
