package com.shachar.coupons.beans;

import com.shachar.coupons.enums.CompanyType;

public class Company {
	
	private long companyId;
	private String companyName;
	private String adress;
	private String phone;
	private CompanyType type;
	
	public Company(long companyId, String companyName, String adress, String phone, CompanyType type) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.adress = adress;
		this.phone = phone;
		this.type = type;
	}
	
	
	public Company(String companyName, String adress, String phone, CompanyType type) {
		super();
		this.companyName = companyName;
		this.adress = adress;
		this.phone = phone;
		this.type = type;
	}


	public Company() {
	}


	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long id) {
		this.companyId = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String compenyName) {
		this.companyName = compenyName;
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
	@Override
	public String toString() {
		return "Company [id=" + companyId + ", company name=" + companyName + ", adress=" + adress + ", phone=" + phone
				+ ", type of the company=" + type  + "]";
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CompanyType getType() {
		return type;
	}

	public void setType(CompanyType type) {
		this.type = type;
	}
}
