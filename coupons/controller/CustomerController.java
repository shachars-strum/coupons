package com.shachar.coupons.controller;

import java.util.ArrayList;
import java.util.Calendar;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Customer;
import com.shachar.coupons.dao.CustomerDao;
import com.shachar.coupons.dao.Icustomer;
import com.shachar.coupons.dao.UserDao;

public class CustomerController {
	private UserController usreController;
	private UserDao userDao;
	private Customer customer;
	private CustomerDao customerDao;
	private PurchaseController purchaseController;
	private UserController userController;



	public CustomerController() {
		super();
		this.usreController = new UserController();
		this.customer = new Customer() ;
		this.customerDao = new CustomerDao();
		this.purchaseController=new PurchaseController();
		this.userController = new UserController();
	}


	public long createCustomer(Customer customer) throws Exception {

		if (customer.getDateOfBirth().after(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the date of birth is not valid");
		}

		long userId = usreController.createUser(customer.getUser());
		customer.setCustomerId(userId);
		this.customerDao.createCustomer(customer);
		return userId;
	}

	public void deleteCustomer(long customerId) throws Exception {

		if (this.customerDao.isCustomerExistByCustomerId(customerId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the customer id is not exist");
		}
		this.purchaseController.deletePurchasesCustomer(customerId);
		this.userController.deleteUser(customerId);
		this.customerDao.deleteCustomer(customerId);
	}

	public void upDateCustomer(Customer customer) throws Exception {
		if (this.customerDao.isCustomerExistByCustomerId(customer.getCustomerId())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the customer id is not exist");
		}

		if (customer.getDateOfBirth().after(Calendar.getInstance(Calendar.getInstance().getTimeZone()).getTime())) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the date of birth is not valid");
		}

		this.customerDao.upDateCustomer(customer);

	}

	public Customer getCustomerByCustomerId(long customerId) throws Exception {
		if (this.customerDao.isCustomerExistByCustomerId(customerId)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the customer id is not exist");
		}

		return this.customerDao.getCustomerByCustomerId(customerId);
	}

	public ArrayList<Customer> getAllCustomers() throws Exception{
		return this.customerDao.getAllCustomers();
	}

}



