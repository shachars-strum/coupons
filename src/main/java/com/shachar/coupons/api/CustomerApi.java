package com.shachar.coupons.api;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shachar.coupons.data.UserLoginData;
import com.shachar.coupons.entities.Customer;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.logic.CustomerController;



@RestController
@RequestMapping("/customers")
public class CustomerApi {

	@Autowired
	private CustomerController customersController;
	
	@PostMapping
	public long createCustomer (@RequestBody Customer customer) throws ApplicationException {
		System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssss");

		return customersController.createCustomer(customer);
	}
	
	@PutMapping
	public void updateCustomer (@RequestBody Customer customer) throws ApplicationException {
		customersController.updateCustomer(customer);
	}
	

	
	@GetMapping ("{customerId}")
	public Customer getCustomer (@PathVariable ("customerId") long customerId) throws ApplicationException {
		return customersController.getCustomer(customerId);
	}
	
	@GetMapping()
	public List<Customer> getAllCustomers () throws ApplicationException{
		return customersController.getAllCustomers();
	}
	
	@GetMapping("/customerDeteils")
	public Customer getCustomer (HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		Customer customer = customersController.getCustomer(userData.getId());
		return this.customersController.getCustomer(customer.getId());
	}
	

}
