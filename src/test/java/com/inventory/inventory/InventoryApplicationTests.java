package com.inventory.inventory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inventory.controller.ProductController;
import com.inventory.entity.Product;
import com.inventory.service.ProductService;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InventoryApplicationTests {
    @Autowired
	ProductController controller;
    @MockBean
    ProductService service;
    @Test
    public void getAllProductsTest() {
    	Product product1=new Product(1,"IPhone","Finished Product",1,60000.0d,0);
    	Product product2=new Product(2,"Samusung","Finished Product",1,60000.0d,0);
    	List<Product> productList=new ArrayList<Product>();
    	productList.add(product1);
    	productList.add(product2);
    	when(service.getAllProducts()).thenReturn(productList);
    	assertEquals(2, controller.getAllProducts().size());
    }
    @Test
    public void getProductByIdTest() {
    	Product product=new Product(3,"IPad","Finished Product",1,60000.0d,0);
    	when(service.getProductById(product.getProductId())).thenReturn(product);
    	Product actualProduct=controller.getProductById(product.getProductId());
    	assertEquals(product.getProductName(), actualProduct.getProductName());
    }
    @Test
    public void createProductTest() {
    	Product product=new Product(3,"IPad","Finished Product",1,60000.0d,0);
    	ResponseEntity<String> responseEntity = new ResponseEntity<String>("Product stored successfully!!", HttpStatus.CREATED);
    	when(service.createProduct(product)).thenReturn(responseEntity);
    	ResponseEntity<String> result = controller.createProduct(product);
    	assertEquals("Product stored successfully!!", result.getBody());
    }
    @Test
    public void updateProductTest() {
    	Product product=new Product(3,"IPad","Finished Product",1,60000.0d,0);
    	ResponseEntity<String> responseEntity = new ResponseEntity<String>("Product updated successfully!!", HttpStatus.OK);
    	when(service.updateProduct(product)).thenReturn(responseEntity);
    	ResponseEntity<String> result = controller.updateProduct(product);
    	assertEquals("Product updated successfully!!", result.getBody());
    }
    @Test
    public void deleteProductTest() {
    	Product product=new Product(3,"IPad","Finished Product",1,60000.0d,0);
    	ResponseEntity<String> responseEntity = new ResponseEntity<String>("Product deleted successfully!!", HttpStatus.OK);
    	when(service.deleteProduct(product.getProductId())).thenReturn(responseEntity);
    	ResponseEntity<String> result = controller.deleteProduct(product.getProductId());
    	assertEquals("Product deleted successfully!!", result.getBody());
    }
    

}
