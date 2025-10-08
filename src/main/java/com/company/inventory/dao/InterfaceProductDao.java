package com.company.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Product;


public interface InterfaceProductDao extends CrudRepository<Product, Long>{

}
