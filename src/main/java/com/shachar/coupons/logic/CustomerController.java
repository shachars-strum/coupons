package com.shachar.coupons.logic;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shachar.coupons.dao.ICustomerDao;
import com.shachar.coupons.entities.Customer;
import com.shachar.coupons.entities.User;
import com.shachar.coupons.enums.ErrorTypes;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.exceptions.ApplicationException;



@Controller
public class CustomerController {

	@Autowired
	private ICustomerDao customersDao;
	@Autowired
	private UserController usersController;
	//	@Autowired
	//	private PurchasesController purchasesController;

	public long createCustomer(Customer customer) throws ApplicationException {
		customerValidations(customer);
		this.usersController.userValidations(customer.getUser());
//		customer.getUser().setPassword(String.valueOf(customer.getUser().getPassword().hashCode()));
				long userId = usersController.createUser(customer.getUser());
				customer.setId(userId);
				System.out.println(customer);

		try {
			 this.customersDao.save(customer);

		} catch (Exception e) {
			//			usersController.deleteUser(userId);
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_CUSTOMER, "Failed to create cutomer", e);
		}
		
		return customer.getId();
	}

	private void customerValidations(Customer customer) throws ApplicationException {

		if (customer.getUser().getUserType() != UserType.customer) {
			throw new ApplicationException(ErrorTypes.INVALID_USERTYPE, "Invalid usertype.");
		}
		if (customer.getAmountOfKids() < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_KIDS, "Invalid amount of kids.");
		}

	}

	public void updateCustomer(Customer customer) throws ApplicationException {
		customerValidations(customer);
		customerUserValidation(customer);
		try {
			this.customersDao.save(customer);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_CUSTOMER, "Failed to update cutomer");	
		}
	}
	private void customerUserValidation(Customer customer) throws ApplicationException {
		User user = customer.getUser();
		usersController.isUserExsitById(user.getId());
		if (user.getId() != customer.getId()) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "User id and customer id doesn't match");
		}

	}

	public Customer getCustomer(long customerId) throws ApplicationException {
		isCustomerExistsById(customerId);
		try {
			return this.customersDao.findById(customerId);
		}catch (Exception e){
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_CUSTOMER, "Failed to get cutomer");
		}
	}

		public List<Customer> getAllCustomers() throws ApplicationException {
			try {
				return (List<Customer>) this.customersDao.findAll();
			}catch(Exception e) {
				throw new ApplicationException(ErrorTypes.FAIL_TO_GET_CUSTOMERS, "Failed to get cutomers");

			}

		}

		public void isCustomerExistsById(long customerId) throws ApplicationException {
			if (this.customersDao.findById(customerId) == null) {
				throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "There is no customer with this ID");
			}
		}
	}




