package com.shachar.coupons.controller;

import java.util.ArrayList;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.dao.CompanyDao;
import com.shachar.coupons.dao.CouponDao;

public class CompanyController {

	private CompanyDao companyDao;
	private UserController userController;
	private CouponController couponController;




	public CompanyController() {
		super();

		this.companyDao = new CompanyDao();
		this.couponController = new CouponController();
	}

	public long createCompany(Company company) throws Exception  {
		validationToCreateCompany(company );
		long id = companyDao.createCompany(company);
		company.setCompanyId(id);
		return id;


	}

	public Company getCompanyByCompanyId(long companyId) throws Exception {
		if(this.companyDao.isCompanyExistByCompanyId(companyId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "company id not exist");
		}
		return this.companyDao.getCompanyByCompanyId(companyId);

	}

	public void deleteCompany(long companyId) throws Exception {
		if(this.companyDao.isCompanyExistByCompanyId(companyId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "company id not exist");
		}
		this.couponController.deleteCouponsCompany(companyId);
		this.userController.deleteUserByCompanyId(companyId);
		this.companyDao.deleteCompany(companyId);

	}

	public void upDateCompany(Company company) throws Exception {

		validationToCreateCompany(company );
		this.companyDao.upDateCompany(company);

	}

	public ArrayList<Company> getAllCompanies() throws Exception {
		return companyDao.getAllCompanies();
	}

	private void validationToCreateCompany(Company company) throws Exception {

		if(companyDao.isCompanyExistByCompanyName(company.getCompanyName())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "company already exist");
		}
	}

	
	public void isCompanyExistByCompanyId(long companyId) throws ApplicationException {
		if(this.companyDao.isCompanyExistByCompanyId(companyId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "company id not exist");

		}
	}
	

}
