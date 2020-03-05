package com.shachar.coupons.beans;

import com.shachar.coupons.enums.UserType;

public class User {

	private long userId;
	private String userName;
	private int passwordHash;
	private Long companyId;
	private UserType userType;


public User (String userName, int passwordHash, UserType userType) {
	this.userName = userName;
	this.passwordHash=passwordHash;
	this.userType = userType;
}

	public User(long userId, String userName, int passwordHash, Long companyId, UserType userType) {
		this(userName, passwordHash, companyId, userType);
		this.userId = userId;

	}



	public User(String userName, int passwordHash, Long companyId, UserType userType) {
		super();
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.companyId = companyId;
		this.userType = userType;
	}



	public User() {

	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash (int passwordHash) {
		this.passwordHash=passwordHash;
	}

	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}



	public Long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}



	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "user [id=" + userId + ", user name=" + userName + ", password=" + passwordHash + ", company id=" + companyId
				+ ", the user type=" + userType  + "]";
	}


}

