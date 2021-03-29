package com.dnc.coupon.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "Coupon")
public class Coupon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7521974749892898440L;
	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_COUPON", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	private int id;
	private String name;
	private String description;
	private Category category;
	private int value;

}
	