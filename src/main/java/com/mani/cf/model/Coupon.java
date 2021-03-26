package com.mani.cf.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import com.mani.cf.constant.Category;

@Entity
@RedisHash(value = "Coupon")
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Category category;
	private int value;
	
	public Coupon() {
		super();
	}
	

	public Coupon( String name, String description, Category category, int value) {
		super();
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
