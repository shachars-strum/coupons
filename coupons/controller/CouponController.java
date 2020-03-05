package com.shachar.coupons.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.dao.CompanyDao;
import com.shachar.coupons.dao.CouponDao;
import com.shachar.coupons.enums.CouponscCategory;

public class CouponController{
	private CouponDao couponDao;
	private CompanyController companyController;




	public CouponController() {
		super();
		this.couponDao = new CouponDao();
		this.companyController=companyController;

	}



	public  long createCoupon(Coupon coupon) throws Exception {

		this.couponDao.isCompanyExistByCompanyId(coupon.getCompanyId());

		couppnValidation(coupon);
		long id = couponDao.createCoupon(coupon);
		coupon.setCouponId(id);
		return id;
	}

	private void couppnValidation(Coupon coupon) throws ApplicationException {
		if (coupon.getMaximumAmountOfPurchases() < 1) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Number of coupons per purchase must be at least one ");
		}
		expriationDat(coupon);
		creationDate(coupon);

	}
	private void creationDate(Coupon coupon) throws ApplicationException {
		if (coupon.getCreationCouponDate()==null) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the exeperation Date worng");
		}		
		if(coupon.getCreationCouponDate().before(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the exeperation Date worng");
		}
		if(coupon.getCreationCouponDate().after(coupon.getExpriationDate())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the creation  Date worng");

		}
	}



	private void expriationDat(Coupon coupon) throws ApplicationException {
		if (coupon.getExpriationDate().before(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the exeperation Date worng");
		}
	}

	public Coupon getCouponByCouponId(long couponId) throws ApplicationException {

		if(this.couponDao.isCouponExistByCouponId(couponId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "coupon id not exist");
		}
		return this.couponDao.getCouponByCouponId(couponId);
	}

	public void deleteCoupon(long couponId) throws Exception {

		if(this.couponDao.isCouponExistByCouponId(couponId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "coupon id not exist");
		}
		this.couponDao.deleteCoupon(couponId);
	}

	public void upDateCoupon (Coupon coupon) throws Exception {
		if(this.couponDao.isCouponExistByCouponId(coupon.getCouponId())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "coupon id not exist");
		}
		couppnValidation(coupon);
		couponDao.updateCoupon(coupon);
	}

	public ArrayList<Coupon> getAllCoupons ( ) throws ApplicationException {
		return this.couponDao.getAllCoupons();
	}

	public void deleteCouponsCompany(long companyId) throws ApplicationException {
		this.companyController.isCompanyExistByCompanyId(companyId);

		List<Coupon>companyCoupons = this.couponDao.getCompanyCoupons(companyId);
		if(companyCoupons == null) {
			return;
		}
		for (Coupon coupon : companyCoupons) {
			this.couponDao.deleteCouponsCompanies(coupon.getCompanyId());
		}
	}

	public List<Coupon> getCouponsByCategory(CouponscCategory category) throws ApplicationException {
		return this.couponDao.getCouponsByCategory(category);
	}

	public List<Coupon>getCompanyCoupons(long companyId) throws ApplicationException{
		return couponDao.getCompanyCoupons(companyId);
	}

	public void isCouponExistByCouponId(long couponId) throws ApplicationException {
		if(this.couponDao.isCouponExistByCouponId(couponId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "coupon id not exist");
		}	}

	public void updateAmpuntOfCoupons(long couponId, int amountOfCoupons ) throws ApplicationException {
		Coupon coupon = couponDao.getCouponByCouponId(couponId);
		int newAmountOfCoupons = coupon.getMaximumAmountOfPurchases()-amountOfCoupons;
		if(newAmountOfCoupons<0) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "There are hargion in the amount of coupons ");
		}
		couponDao.updateAmpuntOfCoupons(couponId, newAmountOfCoupons);

	}

	public List<Coupon> getPurchasedCoupons() throws Exception{
		return this.couponDao.getPurchasedCoupons();
	}
}
