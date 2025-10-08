package com.company.inventory.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.InterfaceProductService;
import com.company.inventory.util.Util;

@CrossOrigin( origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductsRestController {
	
	private InterfaceProductService productService;
	
	public ProductsRestController(InterfaceProductService productService) {
		super();
		this.productService = productService;
	}
	
	/**
	 * SAVE PRODUCTS
	 * @param image
	 * @param name
	 * @param price
	 * @param account
	 * @param categoryId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> saveProduct(
			@RequestParam("image") MultipartFile image,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int account,
			@RequestParam("categoryId") Long categoryId	) throws IOException {
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setAccount(account);
		product.setImage(Util.compressZLib(image.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.saveProduct(product, categoryId);
		
		return response;
	}
	

}
