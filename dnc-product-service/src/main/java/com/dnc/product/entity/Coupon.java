package com.dnc.product.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties("products")*/
@Getter
@Setter
public class Coupon implements Serializable{

	/*
	 * private static final long serialVersionUID = -7521974749892898440L; 
	 * @Id
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private int id;
	private String name;
	private String description;
	private Category category;
	private int value;
	
	
	/*
	 * @ManyToMany(targetEntity = Product.class, mappedBy = "coupons", cascade =
	 * {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
	 * CascadeType.REFRESH}) private List<Product> products;
	 */
    
}
	