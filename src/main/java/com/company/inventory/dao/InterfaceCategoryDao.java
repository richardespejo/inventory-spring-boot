package com.company.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Category;

public interface InterfaceCategoryDao extends CrudRepository<Category , Long> {

}
