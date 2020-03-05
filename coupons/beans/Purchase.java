package com.shachar.coupons.beans;

import java.sql.Date;

public class Purchase {

	private long purchaseId;
	private Date implementDate;
	private long couponId;
	private Long customerId;
	private int amountOfCoupons;
	
	
	
	public Purchase(long purchaseId, Date implementDate, long couponId, Long customerId, int amountOfCoupons) {
		super();
		this.purchaseId = purchaseId;
		this.implementDate = implementDate;
		this.couponId = couponId;
		this.customerId = customerId;
		this.amountOfCoupons = amountOfCoupons;

	}
	
	public Purchase(Date implementDate, long couponId, Long customerId, int amountOfCoupons) {
		super();
		this.implementDate = implementDate;
		this.couponId = couponId;
		this.customerId = customerId;
		this.amountOfCoupons = amountOfCoupons;
	
	}
	
	public Purchase(long purchaseId, Date implementDate, int amountOfCoupons) {
		super();
		this.purchaseId = purchaseId;
		this.implementDate = implementDate;
	}
	
	

	public Purchase() {
	
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getImplementDate() {
		return implementDate;
	}

	public void setImplementDaten(Date implementDate) {
		this.implementDate = implementDate;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getAmountOfCoupons() {
		return amountOfCoupons;
	}

	public void setAmountOfCoupons(int amountOfCoupons) {
		this.amountOfCoupons = amountOfCoupons;
	}
	
	@Override
	public String toString() {
		return "purchases [purchase id=" + purchaseId + ", final Date For Realization=" + implementDate + ", coupon Id=" + couponId + ", customer id=" + customerId
				+ ", amount Of Couponse=" + amountOfCoupons  + "]";
	}


	
	
}
