package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Product;


public interface InterfaceProductDao extends CrudRepository<Product, Long>{
	
	// busqueda personalizada por nombre por JPQL
	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByNameLike(String name);
	
	// busqueda personalizada por nombre con spring data
	List<Product> findByNameContainingIgnoreCase(String name);

}
