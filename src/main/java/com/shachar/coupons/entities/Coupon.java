package com.shachar.coupons.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shachar.coupons.enums.CouponsCategory;

@Entity
@Table (name = "coupons")
public class Coupon {

	// Variables--------------------


	@Id
	@GeneratedValue
	private long id;
	
	@Column (nullable = false)
	private String title;
	
	@Column (nullable = false)
	private float price;
	
	@ManyToOne 
	private Company company;
	
	@Column (nullable = false)
	private int availableQuantity;
	
	@Column (nullable = false)
	private Date expriationDate;
	
	@Column (nullable = false)
	private Date creationCouponDate;
	
	@Column (nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponsCategory category;
	
	@JsonIgnore
	@OneToMany (mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchase> purchases;
	
	
	

	// Constructors--------------------

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Coupon() {

	}

//	public Coupon(String title, float price, Company company, int availableQuantity, Date creationCouponDate, Date expriationDate,
//			CouponsCategory category) {
//		this.title = title;
//		this.price = price;
//		this.company = company;
//		this.availableQuantity =  availableQuantity;
//		this.expriationDate = expriationDate;
//		this.creationCouponDate = creationCouponDate;
//		this.category = category;
//	}
//
//	public Coupon(long id, String title, float price, Company company, int availableQuantity, Date creationCouponDate,
//			Date expriationDate, CouponsCategory category) {
//		this.id = id;
//		this.title = title;
//		this.price = price;
//		this.company = company;
//		this.availableQuantity =  availableQuantity;
//		this.expriationDate = expriationDate;
//		this.creationCouponDate = creationCouponDate;
//		this.category = category;
//	}
//	
//	public Coupon(long id, String title, float price, int  availableQuantity, Date creationCouponDate, Date expriationDate,
//			CouponsCategory category) {
//		this.id = id;
//		this.title = title;
//		this.price = price;
//		this.availableQuantity =  availableQuantity;
//		this.expriationDate = expriationDate;
//		this.creationCouponDate = creationCouponDate;
//		this.category = category;
//	}

	// Getters and Setters--------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Date getExpriationDate() {
		return expriationDate;
	}

	public void setExpriationDate(Date expriationDate) {
		this.expriationDate = expriationDate;
	}

	public Date getCreationCouponDate() {
		return creationCouponDate;
	}

	public void setCreationCouponDate(Date creationCouponDate) {
		this.creationCouponDate = creationCouponDate;
	}

	public CouponsCategory getCategory() {
		return category;
	}

	public void setCategory(CouponsCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", price=" + price + ", company=" + company
				+ ", availableQuantity=" + availableQuantity + ", expriationDate=" + expriationDate
				+ ", creationCouponDate=" + creationCouponDate + ", category=" + category + ", purchases=" + purchases
				+ "]";
	}
}
