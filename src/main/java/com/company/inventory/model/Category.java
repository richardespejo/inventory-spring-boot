package com.company.inventory.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data //Utilizacion de Lombok
@Entity //transformo la clase en una entidad de base de datos
@Table(name="category") //nombre de como aparecera la tabla en la base de datos
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )
	private Long id;
	private String name;
	private String description;

	

	

}
