package com.shachar.coupons.beans;

import java.sql.Date;

import com.shachar.coupons.enums.UserType;

public class Customer {
	private long customerId;
	private int amountOfKids;
	private Date dateOfBirth;
	private boolean isMaried;
	private String adress;
	private String phone;
	private  User user;


	
	public Customer (String userName, int passwordHash, UserType userType, boolean isMaried, int amountOfKids, Date dateOfBirth,  String adress, String phone) {
		this.user = new User(userName, passwordHash, userType);
		this.amountOfKids = amountOfKids;
		this.dateOfBirth = dateOfBirth;
		this.isMaried = isMaried;
		this.adress = adress;
		this.phone = phone;
	}
	
	public Customer(String userName, int passwordHash, UserType userType,long customerId, boolean isMaried, int amountOfKids, Date dateOfBirth,  String adress, String phone) {
		super();
		this.customerId =customerId;
		this.user = new User(userName, passwordHash, userType);
		this.amountOfKids = amountOfKids;
		this.dateOfBirth = dateOfBirth;
		this.isMaried = isMaried;
		this.adress = adress;
		this.phone = phone;
		this.user = new User();

	}
	
	public Customer(long customerId, boolean isMaried, int amountOfKids, Date dateOfBirth,  String adress, String phone) {
		super();
		this.customerId =customerId;
		this.amountOfKids = amountOfKids;
		this.dateOfBirth = dateOfBirth;
		this.isMaried = isMaried;
		this.adress = adress;
		this.phone = phone;
		this.user = new User();

	}




	public Customer(boolean isMaried, int amountOfKids, Date dateOfBirth,  String adress, String phone) {
		super();
		this.amountOfKids = amountOfKids;
		this.dateOfBirth = dateOfBirth;
		this.isMaried = isMaried;
		this.adress = adress;
		this.phone = phone;


	}



	public Customer() {

	}


	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long id) {
		this.customerId = id;
	}

	public int getAmountOfKids() {
		return amountOfKids;
	}

	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setAge(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isMaried() {
		return isMaried;
	}

	public void setMaried(boolean isMaried) {
		this.isMaried = isMaried;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [id=" + customerId + ", is Maried=" + isMaried + ", amount of kids=" + amountOfKids + ", date Of Birth=" + dateOfBirth
				+ ", addres= " + adress  +"phone="+phone +"]" ;
	}







}
