package com.company.inventory.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.InterfaceCategoryDao;
import com.company.inventory.dao.InterfaceProductDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.util.Util;

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

		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
				
		try {

			Optional<Product> product =  productDao.findById(id);
			
			if( product.isPresent() ) {
				
				byte[] imageDescompressed = Util.decompressZLib(product.get().getImage());
				product.get().setImage(imageDescompressed);
				list.add(product.get());
				response.getProductResponse().setProduct(list);
				response.setMetadata("respuesta ok", "00", "Respuesta exitosa");

			}else {
				response.setMetadata("respouesta no ok", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>( response ,HttpStatus.NOT_FOUND );

			}

			
		} catch (Exception e) {
			
			response.setMetadata("respouesta no ok", "-1", "Error al guardar producto");
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>( response , HttpStatus.INTERNAL_SERVER_ERROR );

		}
		
		return new ResponseEntity<ProductResponseRest>( response , HttpStatus.OK );
		
	}
	
	
	
	@Override
	@Transactional( readOnly = true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {

		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		
		try {

			listAux = productDao.findByNameContainingIgnoreCase(name);
			
			if( listAux.size() > 0) {
				
				listAux.stream().forEach( (prod) -> {
					byte[] imageDescompressed = Util.decompressZLib(prod.getImage());
					prod.setImage(imageDescompressed);
					list.add(prod);
				});
				
				response.getProductResponse().setProduct(list);
				response.setMetadata("respuesta ok", "00", "Respuesta exitosa");

			}else {
				response.setMetadata("respouesta no ok", "-1", "Productos no encontrados");
				return new ResponseEntity<ProductResponseRest>( response ,HttpStatus.NOT_FOUND );

			}

		} catch (Exception e) {
			
			response.setMetadata("respouesta no ok", "-1", "Error al buscar productos");
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>( response , HttpStatus.INTERNAL_SERVER_ERROR );

		}

		return new ResponseEntity<ProductResponseRest>( response , HttpStatus.OK );
	}
	

	
	@Override
	public ResponseEntity<ProductResponseRest> saveProduct(Product product , Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
				
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
		
		ProductResponseRest response = new ProductResponseRest();
		
		try {
			
			productDao.deleteById( id );
			response.setMetadata("Respuesta ok", "00", "Eliminación exitosa");
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al eliminar producto");
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR );
			
		}
		
		return new ResponseEntity<ProductResponseRest>( response , HttpStatus.OK );
	}




}
