package com.company.inventory.services;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.InterfaceCategoryDao;
import com.company.inventory.dao.InterfaceProductDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements InterfaceProductService {
	
	private InterfaceCategoryDao categoryDao;
	private InterfaceProductDao productDao; 

	public ProductServiceImpl(InterfaceCategoryDao categoryDao , InterfaceProductDao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}


	@Override
	@Transactional( readOnly = true)
	public ResponseEntity<ProductResponseRest> searchProduct() {
		
		ProductResponseRest response = new ProductResponseRest();

		try {
			
			List<Product> products = (List<Product>) productDao.findAll();
			
			response.getProductResponse().setProduct(products);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar productos");
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR );
			
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK );
	}
	

	@Override
	@Transactional( readOnly = true)
	public ResponseEntity<ProductResponseRest> searchProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public ResponseEntity<ProductResponseRest> saveProduct(Product product , Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		System.out.print(product.getImage());
		
		try {
			//Busco la categoria asociada al producto
			Optional<Category> category =  categoryDao.findById(categoryId);
			
			if( category.isPresent() ) {
				product.setCategory(category.get());
			}else {
				response.setMetadata("respouesta no ok", "-1", "Categoria no asociada al producto");
				return new ResponseEntity<ProductResponseRest>( response , HttpStatus.NOT_FOUND );
			}
			
			//Guardado del producto
			Product productSaved = productDao.save(product);
			
			if( productSaved != null ) {
				list.add(productSaved);
				response.getProductResponse().setProduct(list);
				response.setMetadata("respuesta ok", "00", "Producto guardado");
			}else {
				response.setMetadata("respouesta no ok", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>( response , HttpStatus.BAD_REQUEST );

			}
			
		} catch (Exception e) {
			response.setMetadata("respouesta no ok", "-1", "Error al guardar producto");
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>( response , HttpStatus.INTERNAL_SERVER_ERROR );

		}
		
		return new ResponseEntity<ProductResponseRest>( response , HttpStatus.OK );

	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> updateProduct(Product product, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
