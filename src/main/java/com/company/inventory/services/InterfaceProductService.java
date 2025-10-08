package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;

public interface InterfaceProductService {
	
	public ResponseEntity<ProductResponseRest> searchProduct();
	
	public ResponseEntity<ProductResponseRest> searchProductById(Long id);

	public ResponseEntity<ProductResponseRest> saveProduct(Product product , Long categoryId);
	
	public ResponseEntity<ProductResponseRest> updateProduct(Product product , Long id);
	
	public ResponseEntity<ProductResponseRest> deleteProductById(Long id);

}
