package com.shachar.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shachar.coupons.data.UserLoginData;
import com.shachar.coupons.databeans.SuccessfulLoginData;
import com.shachar.coupons.databeans.UserLoginDetails;
import com.shachar.coupons.entities.Coupon;
import com.shachar.coupons.entities.User;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.logic.UserController;


@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UserController usersController;

	@PostMapping
	public long createUser(@RequestBody User user) throws ApplicationException  {
		return usersController.createUser(user);
	}
	
	@PutMapping
	public void updateUser (@RequestBody User user) throws ApplicationException {
		usersController.updateUser(user);
	}

	@GetMapping ("{userId}")
	public User getUser (@PathVariable ("userId") long userId) throws ApplicationException {
		return usersController.getUser(userId);
	}

	@GetMapping()
	public List<User> getAllUsers () throws ApplicationException{
		return usersController.findAllUsers();
	}

	@DeleteMapping("{userId}")
	public void deleteUser (@PathVariable("userId") long userId) throws ApplicationException {
		usersController.deleteUser(userId);
	}
	
	@GetMapping ("/byCompany")
	public List<User> getUsersByCompanyId (HttpServletRequest request) throws ApplicationException{
System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Long companyId = userLoginData.getCompanyId();
		System.out.println(companyId+"$$$$$$$$$$$$$$$$$$$$$");
		return usersController.getUsersByCompanyId(companyId);
	}
	
	@PostMapping("/login")
	public SuccessfulLoginData login (@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException {
		return usersController.login(userLoginDetails);
	}
}




