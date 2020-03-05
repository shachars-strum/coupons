package com.shachar.coupons.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.beans.Purchase;
import com.shachar.coupons.dao.CouponDao;
import com.shachar.coupons.dao.CustomerDao;
import com.shachar.coupons.dao.PurchaseDao;


public class PurchaseController {

	private Purchase purchase;
	private PurchaseDao purchaseDao;
	private CouponController couponController;



	public PurchaseController() {
		super();
		this.purchase = new Purchase();
		this.purchaseDao = new PurchaseDao();
		this.couponController = new CouponController();

	}

	public long createPurchase(Purchase purchase) throws ApplicationException {

		validationPurchase(purchase);
		long id =purchaseDao.createPurchase(purchase);
		int amountOfCoupons = purchase.getAmountOfCoupons();
		long couponId = purchase.getCouponId();
		couponController.updateAmpuntOfCoupons(couponId, amountOfCoupons);
		purchase.setPurchaseId(id);

		return id;


	}


	private void validationPurchase(Purchase purchase) throws ApplicationException {


		if(purchase.getImplementDate().before(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the Implement Date worng");
		}

		if (purchase.getImplementDate().before(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the Final Date For Realization worng");
		}
		if(purchase.getAmountOfCoupons()<1) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "The number of coupons should be at least one");
		}
	}



	public void deletePurchase (long id) throws ApplicationException {

		if(this.purchaseDao.isPurchaseExistByPurchaseId(id)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "purchase id not exist");	
		}

		this.purchaseDao.deletePurchase(id);
	}

	public Purchase getPurchasebyPurchaseId(long id) throws ApplicationException {
		if(this.purchaseDao.isPurchaseExistByPurchaseId(id)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "purchase id not exist");	
		}
		return this.purchaseDao.getPurchasebyPurchaseId(id);
	}

	public void updatePurchase(Purchase purchase) throws Exception {
		updateValidationPurchase(purchase);
		this.purchaseDao.updatePurchase(purchase);
	}

	private void updateValidationPurchase(Purchase purchase) throws ApplicationException  {

		if(purchase.getImplementDate().before(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the Final Date For Realization worng");
		}
		if(purchase.getAmountOfCoupons()<1) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "The number of coupons should be at least oneg");
		}
	}

	public ArrayList<Purchase> getAllpurchases() throws ApplicationException {
		return purchaseDao.getAllPurchase();
	}

	public void deletePurchasesCustomer (long customerId) throws ApplicationException {
		List<Purchase>purchaseCustomer = this.purchaseDao.getPurchasesCustomer(customerId);
		if(purchaseCustomer == null) {
			return;
		}
		for(Purchase purchase : purchaseCustomer) {
			purchaseDao.deletePurchasesCustomer(purchase.getCustomerId());
		}
	}

	public void deletePurchasesCoupon (long couponId) throws ApplicationException {
		couponController.isCouponExistByCouponId(couponId);
		purchaseDao.deletePurchasesCoupon(couponId);
	}


}




