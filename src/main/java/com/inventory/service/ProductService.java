package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts(){
		List<Product> productList=(List<Product>) productRepository.findAll();
		return productList;
	}
	public ResponseEntity<String> createProduct(Product product){
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
		
		return productRepository.findById(productId).get();
	}
}
