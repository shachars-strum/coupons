package com.shachar.coupons.enums;

public enum ErrorTypes {

	FAIL_TO_GENERATE_ID (601, "Failed to generate id" ,false),
	COMPANY_CREATION_FAILED (602, "Failed to create company" ,false),
	COMPANY_UPDATE_FAILED (603, "Failed to update company" ,false),
	GENERAL_ERROR (604, "Genaral error" ,true),
	FAIL_TO_CHECK_IF_COMPANY_EXIST (605, "Failed to check if company exist" ,false),
	FAIL_TO_GET_COMPANY (606, "Failed to get company" ,false),
	INVALID_COMPANY_NAME (607, "Invalid company name" ,false),
	INVALID_EMAIL_ADDRESS (608, "Invalid email address" ,false),
	INVALID_COMPANY_ID (609, "Invalid company id" ,false),
	FAIL_TO_CREATE_COUPON (610, "Failed to create coupon" ,false),
	FAIL_TO_UPDATE_COUPON (611, "Failed to update coupon" ,false),
	FAIL_TO_DELETE_COUPON (612, "Failed to delete coupon" ,false),
	INVALID_COUPON (613, "Invalid coupon" ,false),
	INVALID_COUPONS (614, "Invalid coupons" ,false),
	INVALID_COUPON_NAME (615, "Invalid coupon name" ,false),
	INVALID_PRICE (616, "Invalid coupon name" ,false),
	INVALID_DATE (617, "Invalid date" ,false),
	INVALID_COUPON_ID (618, "Invalid coupon id" ,false),
	FAIL_TO_CREATE_CUSTOMER (619, "Failed to create customer" ,false),
	FAIL_TO_UPDATE_CUSTOMER (620, "Failed to update customer" ,false),
	FAIL_TO_DELETE_CUSTOMER (621, "Failed to delete customer" ,false),
	INVALID_CUSTOMER (622, "Invalid customer" ,false),
	INVALID_CUSTOMERS (623, "Invalid customers" ,false), 
	INVALID_USERTYPE (624, "Invalid type of user" ,false),
	INVALID_AMOUNT_OF_KIDS (625, "Invalid amount of kids" ,false),
	INVALID_USER (626, "Invalid user" ,false),
	INVALID_USER_ID (627, "Invalid user id" ,false),
	FAIL_TO_CREATE_USER (628, "Failed to create user" ,false),
	FAIL_TO_DELETE_USER (629, "Failed to delete user" ,false),
	FAIL_TO_UPDATE_USER (630, "Failed to update user" ,false),
	INVALID_USERS (631, "Invalid user" ,false),
	INVALID_USERNAME (632, "Invalid username" ,false),
	INVALID_PASSWORD (633, "Invalid password" ,false),
	INVALID_COMPANY (634, "Invalid company" ,false), 
	FAIL_TO_GET_PASSWORD (635, "Failed to get password" ,false), 
	FAIL_TO_CREATE_PURCHASE (636, "Failed to create password" ,false),
	FAIL_TO_UPDATE_PURCHASE (637, "Failed to update password" ,false),
	FAIL_TO_DELETE_PURCHASE (638, "Failed to delete password" ,false),
	INVALID_PURCHASE (639, "Invalid purchase" ,false),
	FAIL_TO_GET_AVAILABLE_QUANTITY (640, "Failed to get avvailable quantity" ,false),
	INVALID_AMOUNT_OF_ITEMS (641, "Invalid amount of items" ,false),
	FAIL_TO_DELETE_COMPANY(642,"Failed to delete company",false),
	INVALID_PURCHASES (643, "Invalid purchase" ,false),
	FAIL_TO_GET_COMPANIES(644,"failed to get all companeis",false),
	FAIL_TO_GET_COUPON(645,"failed to get coupon",false),
	FAIL_TO_GET_COUPONS(646,"failed to get all coupons",false),
	FAIL_TO_GET_CUSTOMER(647,"failed to get customer",false),
	FAIL_TO_GET_CUSTOMERS(648,"failed to get customers",false),
	FAIL_TO_GET_PURCHASE(649,"failed to get purchase",false),
	FAIL_TO_GET_PURCHASES(650,"failed to get purchases",false),
	FAIL_TO_GET_USER(651,"failed to get user",false),
	FAIL_TO_GET_USERS(652,"failed to get users",false);





	private int errorNumber;
	private String errorMessage;
	private boolean isShowStackTrace;
	
	private ErrorTypes() {
	}

	private ErrorTypes(int errorNumber, String errorMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
		this.isShowStackTrace = isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public void setShowStackTrace(boolean isShowStackTrace) {
		this.isShowStackTrace = isShowStackTrace;
	}
	
	
	
	
}
