package com.shachar.coupons.controller;

import java.util.ArrayList;
import java.util.List;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Customer;
import com.shachar.coupons.beans.User;
import com.shachar.coupons.dao.CompanyDao;
import com.shachar.coupons.dao.UserDao;


public class UserController {

	private User user;
	private Customer customer;
	private UserDao userDao;
	private CompanyController companyController;


	public UserController() {
		super();
		this.customer = new Customer();
		this.user = new User();
		this.userDao = new UserDao();
		//this.companyController = new CompanyController();
	}

	public boolean login(String userName, int passwordHash) throws Exception {
		if(userDao.getUserPasswordHash(userName)==passwordHash) {
			return true;
		}
		return false;
	}

	public long createUser(User user) throws Exception {
		ValidationsForCreateUser(user);
		long id = userDao.createUser(user);
		return id;		
	}

	private void ValidationsForCreateUser(User user) throws ApplicationException {
		validationsForEmailFormation(user.getUserName());	

		if(userDao.isUserExistByUserName(user.getUserName())){
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "User already exist");
		}

	}

	private void validationsForEmailFormation(String email) throws ApplicationException {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if ( !email.matches(regex)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "the mail configuration worng");
		}
		return;		
	}

	public void upDateUser (User user) throws ApplicationException {

		updateValidationsForCreateUser(user);
		this.userDao.upDateUser(user);
	}

	private void updateValidationsForCreateUser(User user) throws ApplicationException {
		validationsForEmailFormation(user.getUserName());	

	}

	public User getUserByUserId(long id) throws Exception {

		if(this.userDao.isUsrExistByUserId(id)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "User id not exist");
		}
		return	this.userDao.getUserByUserId(id);
	}

	public void deleteUser(long id) throws Exception {

		if(this.userDao.isUsrExistByUserId(id)) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "User id not exist");	
		}

		userDao.deleteUser(id);
	}

	public ArrayList<User>getAllUsers() throws Exception{
		return userDao.getAllUsers();
	}

	public void deleteUserByCompanyId(long companyId) throws Exception {
		companyController.isCompanyExistByCompanyId(companyId);
		List<User>companyUser = this.userDao.getCompanyUsers(companyId);
		if(companyUser == null) {
			return;
		}
		for( User user : companyUser) {
			this.userDao.deleteUserByCompanyId(user.getCompanyId());
		}
	}

}
