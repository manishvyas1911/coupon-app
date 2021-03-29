package com.dnc.product.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	private int id;
	private String name;
	private String description;
	private String price;
	private String category;
	
	 /*@ManyToMany(targetEntity = Coupon.class, fetch = FetchType.LAZY,
			 cascade = {CascadeType.PERSIST, 
						 CascadeType.DETACH,
						 CascadeType.MERGE,
						 CascadeType.REFRESH} )
	 @JoinTable(name="t_product_coupons",
		joinColumns=@JoinColumn( name="product_id", referencedColumnName="id"),
	    inverseJoinColumns=@JoinColumn(name="coupon_id", referencedColumnName="id"))
	*/
	@Transient
	private List<Coupon> coupons;
}
