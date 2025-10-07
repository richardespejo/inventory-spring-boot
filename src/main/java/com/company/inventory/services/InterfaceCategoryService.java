package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;

public interface InterfaceCategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);

	public ResponseEntity<CategoryResponseRest> saveCategory(Category category);
	
	public ResponseEntity<CategoryResponseRest> updateCategory(Category category , Long id);
	
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);


}
