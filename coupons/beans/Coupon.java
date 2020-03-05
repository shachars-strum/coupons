package com.shachar.coupons.beans;

import java.sql.Date;

import com.shachar.coupons.enums.CouponscCategory;

public class Coupon {
	private long couponId;
	private float price;
	private String title;
	private long companyId;
	private int maximumAmountOfPurchases;
	private Date expriationDate;
	private CouponscCategory couponCategory;
	private Date creationCouponDate;
	
	
	
	public Coupon(long couponId, float price, String title, long companyId, int maximumAmountOfPurchases,
			Date expriationDate, CouponscCategory couponCategory, Date creationCouponDate) {
		super();
		this.couponId = couponId;
		this.price = price;
		this.title = title;
		this.companyId = companyId;
		this.maximumAmountOfPurchases = maximumAmountOfPurchases;
		this.expriationDate = expriationDate;
		this.couponCategory = couponCategory;
		this.creationCouponDate = creationCouponDate;
	}
	

	public Coupon(float price, String title, long companyId, int maximumAmountOfPurchases, Date expriationDate,
			CouponscCategory couponCategory, Date creationCouponDate) {
		super();
		this.price = price;
		this.title = title;
		this.companyId = companyId;
		this.maximumAmountOfPurchases = maximumAmountOfPurchases;
		this.expriationDate = expriationDate;
		this.couponCategory = couponCategory;
		this.creationCouponDate = creationCouponDate;
	}
	
	public Coupon(long couponId, float price, String title, int maximumAmountOfPurchases,
			Date expriationDate, CouponscCategory couponCategory, Date creationCouponDate) {
		super();
		this.couponId = couponId;
		this.price = price;
		this.title = title;
		this.maximumAmountOfPurchases = maximumAmountOfPurchases;
		this.expriationDate = expriationDate;
		this.couponCategory = couponCategory;
		this.creationCouponDate = creationCouponDate;
	}
	

	
	
	public Coupon() {
	
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public float getPrice() {
		return price;
	}

@Override
	public String toString() {
		return "Coupon [id=" + couponId + ", price=" + price + ", title=" + title + ", companyId=" + companyId
			+ ", maximumAmountOfPurchases=" + maximumAmountOfPurchases + ", expriationDate=" + expriationDate +
			", coupons Catagory= "+ couponCategory + ", creation coupon date= "+ creationCouponDate +"]";
}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getMaximumAmountOfPurchases() {
		return maximumAmountOfPurchases;
	}

	public void setMaximumAmountOfPurchases(int maximumAmountOfPurchases) {
		this.maximumAmountOfPurchases = maximumAmountOfPurchases;
	}

	public Date getExpriationDate() {
		return expriationDate;
	}

	public void setExpriationDate(Date expriationDate) {
		this.expriationDate = expriationDate;
	}
	public CouponscCategory getCouponCategory() {
		return couponCategory;
	}

	public void setCouponCatagory(CouponscCategory couponCategory) {
		this.couponCategory = couponCategory;
	}
	
	public Date getCreationCouponDate() {
		return creationCouponDate;
	}


	public void setCreationCouponDate(Date creationCouponDate) {
		this.creationCouponDate = creationCouponDate;
	}



}
