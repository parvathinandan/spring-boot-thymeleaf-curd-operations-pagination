package org.spring.boot.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE Product_Id = ?")
@Where(clause = "deleted = false")
public @Data class Product {

	@Id
	@Column(name = "Product_Id")
	private Long id;

	@Column(name = "Product_Name")
	private String name;

	@Column(name = "Brand")
	private String brand;

	@Column(name = "Made_In")
	private String madein;

	@Column(name = "Price")
	private Double price;

	private boolean deleted;
}
