package com.shachar.coupons.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shachar.coupons.enums.UserType;


@Entity
@Table (name = "users")
public class User {

	// Variables--------------------
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column (unique = true, nullable = false)
	private String userName;
	
	@Column (nullable = false)
	private String password;
	
	@Column (nullable = true)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@ManyToOne
	private Company company;
	
	

	// Constructors--------------------

	public User() {

	}

	
	public User(String userName, String password, UserType userType) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}


	public User(String userName, String password, UserType userType, Company company) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.company = company;
	}

	public User(long id, String userName, String password, UserType userType, Company company) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.company = company;
	}

	// Getters and Setters--------------------

	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", company=" + company!=null?company.getName():"" + "\n";
	}

}

