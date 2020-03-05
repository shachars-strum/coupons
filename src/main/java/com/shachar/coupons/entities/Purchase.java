package com.shachar.coupons.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name = "purchases")
public class Purchase {

	// Variables--------------------
	
	@Id
	@GeneratedValue
	private long id;
	
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Coupon coupon;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Customer customer;
	
	@Column
	private int amountOfItems;
	
	@CreationTimestamp
	private Timestamp timeStamp;
	
	// Constructors--------------------
	
	public Purchase() {

	}

	public Purchase(Coupon coupon, Customer customer, int amountOfItems, Timestamp timestamp) {
		this.coupon = coupon;
		this.customer = customer;
		this.amountOfItems = amountOfItems;
		this.timeStamp = timestamp;
	}

	public Purchase(long id, Coupon coupon, Customer customer, int amountOfItems, Timestamp timestamp) {
		this.id = id;
		this.coupon = coupon;
		this.customer = customer;
		this.amountOfItems = amountOfItems;
		this.timeStamp = timestamp;
	}

	// Getters and Setters--------------------
	
	public long getId() {
		return id;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setAmountOfItems(int amountOfItems) {
		this.amountOfItems = amountOfItems;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timestamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", coupon=" + coupon==null?coupon.getTitle():"" + ", customer=" + customer==null?customer.getFullName():"" + ", amountOfItems="
				+ amountOfItems + ", timestamp=" + timeStamp + "\n";
	}
			
}
