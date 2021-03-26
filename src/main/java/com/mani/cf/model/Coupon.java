package com.mani.cf.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.mani.cf.constant.Category;

@RedisHash(value = "Coupon")
//@Entity
public class Coupon implements Serializable{


	//@Id
   // @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	private int id;
	private String name;
	private String description;
	private Category category;
	private int value;
	
	public Coupon() {
		super();
	}
	


	

	public Coupon(int id, String name, String description, Category category, int value) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.value = value;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", value=" + value + "]";
	}
	
	
	
	
	
}
