package com.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts(){
		
		return (List<Product>)productRepository.findAll();
	}
	public ResponseEntity<String> createProduct(Product product){
		Product existingProduct=productRepository.findByProductName(product.getProductName());
		System.out.println("existingProduct:"+existingProduct);
		int existingQuantity=0;
		if(existingProduct!=null) {
			existingQuantity=existingProduct.getProductQuantity();
			product.setProductQuantity(existingQuantity+product.getProductQuantity());
		}
			
		productRepository.save(product);
		return new ResponseEntity<String>("Product stored successfully!!", HttpStatus.CREATED);
	}
	public ResponseEntity<String> updateProduct(Product product){
		productRepository.save(product);
		return new ResponseEntity<String>("Product updated successfully!!", HttpStatus.OK);		
	}
	public ResponseEntity<String> deleteProduct(Integer productId){
		productRepository.deleteById(productId);
		return new ResponseEntity<String>("Product deleted successfully!!", HttpStatus.OK);	
		
	}
	public Product getProductById(Integer productId) {
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product=null;
		if(optionalProduct.isPresent())
			product=optionalProduct.get();
		return product;
	}
}
