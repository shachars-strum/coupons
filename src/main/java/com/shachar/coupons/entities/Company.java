package com.shachar.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shachar.coupons.enums.CompanyType;

@Entity
@Table (name = "companies")
public class Company {

	// Variables--------------------
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column (unique = true, nullable = false)
	private String name;
	
	@Column (nullable = false)
	private String phone;
	
	@Column (nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private CompanyType type;
	
	@JsonIgnore
	@OneToMany (mappedBy = "company", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<User> users;
	
	@JsonIgnore
	@OneToMany (mappedBy = "company", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Coupon> coupons;
	
	// Constructors--------------------
	
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Company() {
		
	}

	public Company(String name, String phone, String email, CompanyType type) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}

	public Company(long id, String name, String phone, String email, CompanyType type) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}
	
		public Company(long id, String phone, String email, CompanyType type) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}

	// Getters and Setters--------------------
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CompanyType getType() {
		return type;
	}

	public void setType(CompanyType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", type=" + type
				+ "\n";
	}
}
