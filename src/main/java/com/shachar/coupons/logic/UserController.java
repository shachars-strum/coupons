package com.shachar.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shachar.coupons.dao.IUserDao;
import com.shachar.coupons.data.UserLoginData;
import com.shachar.coupons.databeans.SuccessfulLoginData;
import com.shachar.coupons.databeans.UserLoginDetails;
import com.shachar.coupons.entities.Coupon;
import com.shachar.coupons.entities.User;
import com.shachar.coupons.enums.ErrorTypes;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.exceptions.ApplicationException;

import sun.security.util.PropertyExpander.ExpandException;


@Controller
public class UserController {

	@Autowired
	private IUserDao usersDao;
	@Autowired
	private CompanyController companiesController;
	@Autowired
	private CacheController cacheController;

	private static final String ENCRYPTION_TOKEN_SALT = "AASDFHSJFHJHKAAAAA-3423@#$@#$";



	public long createUser(User user) throws ApplicationException {
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			return this.usersDao.save(user).getId();
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_USER, "Failed to create user");
		}
	}

	public void userValidations(User user) throws ApplicationException {

		if (user.getUserType() == UserType.company) {
			if (user.getCompany() == null) {
				throw new ApplicationException(ErrorTypes.INVALID_COMPANY, "Company id must be specified for a company user.");
			}
			companiesController.isCompanyExsitById(user.getCompany().getId()); 

		}	
//		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//		if ( !user.getUserName().matches(regex)) {
//			throw new ApplicationException(ErrorTypes.INVALID_USERNAME,"Email not valid");
//		}

//		if (user.getPassword().length() > 15 || user.getPassword().length() < 6) {
//			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should be less than 15 and more than 6 characters in length.");
//
//		}
		if (user.getPassword() == user.getUserName()) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password Should not be same as user name");

		}

		String lowerCaseChars = "(.*[a-z].*)";
		if (!user.getPassword().matches(lowerCaseChars)) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one lower case alphabet");

		}
		String numbers = "(.*[0-9].*)";
		if (!user.getPassword().matches(numbers)) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one number.");

		}
	}

	public void updateUser(User user) throws ApplicationException {
		isUserExsitById(user.getId());
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			this.usersDao.save(user);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_USER, "Failed to update user");
		}
	}

	public void deleteUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			this.usersDao.deleteById(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_USER, "Failed to delete user");
		}
	}

	public User getUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			return this.usersDao.findById(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_USER, "Failed to get user");
		}
	}

	public List<User> findAllUsers() throws ApplicationException {
		try {
			return (List<User>) this.usersDao.findAll();
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USERS, "Failed to get users", e);
		}
	}



	public void isUserExsitById (long userId) throws ApplicationException {
		if (this.usersDao.findById(userId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "There is no user with this ID");
		}
	}

	public SuccessfulLoginData login(UserLoginDetails userLoginDetails ) throws ApplicationException {
		System.out.println(userLoginDetails);
		String userName = userLoginDetails.getUserName();
		try {
			User user = usersDao.findByUserName(userName);
			if (user == null) {
				throw new ApplicationException(ErrorTypes.INVALID_USER, "No such user");
			}
			int userHashPassword = Integer.parseInt(user.getPassword());

			System.out.println("1 " + userLoginDetails.getPassword().hashCode());
			System.out.println("2 " +userHashPassword);

			if (userHashPassword != userLoginDetails.getPassword().hashCode()) {

				throw new ApplicationException(ErrorTypes.INVALID_USER, "Invalid password");
			}

			long id = user.getId();
			UserType userType = user.getUserType();
			Long companyId;
			if (userType == UserType.company) {
				companyId = user.getCompany().getId();
			}
			else {
				companyId = null;
			}
			UserLoginData userLoginData = new UserLoginData(id, userType, companyId);
			int token = generateToken(userLoginDetails);


			cacheController.put(String.valueOf(token), userLoginData);

			return new SuccessfulLoginData(token, userLoginData.getUserType());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_PASSWORD, "Failed get the hash password", e);
		}

	}

	private int generateToken(UserLoginDetails userLoginDetails) {
		String text = userLoginDetails.getUserName() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;
		return text.hashCode();
	}

	public List<User> getUsersByCompanyId(long companyId) throws ApplicationException {
		try {
			System.out.println("gggggggggggggggggggggggggggggggggggggggggggggggggg");
			return this.usersDao.findByCompanyId(companyId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get users by company id");
		}

}
}