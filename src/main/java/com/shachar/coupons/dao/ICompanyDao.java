package com.shachar.coupons.dao;

import org.springframework.data.repository.CrudRepository;

import com.shachar.coupons.entities.Company;
import com.shachar.coupons.entities.User;


public interface ICompanyDao extends CrudRepository<Company, Long> {

	public Company findByName(String name);
	Company findById(long companyId);

	

}





