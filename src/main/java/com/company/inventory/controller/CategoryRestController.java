package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.InterfaceCategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private InterfaceCategoryService service;
	
	/**
	 * GET ALL CATEGORIES
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
		
	}
	
	/**
	 * GET CATEGORY BY ID
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
		
	}
	
	/**
	 * SAVE CATEGORY
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
		
		ResponseEntity<CategoryResponseRest> response = service.saveCategory(category);
		return response;
		
	}
	
	/**
	 * UPDATE CATEGORY
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.updateCategory(category , id);
		return response;
		
	}

}
