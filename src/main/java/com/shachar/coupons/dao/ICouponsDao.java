package com.shachar.coupons.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shachar.coupons.entities.Coupon;
import com.shachar.coupons.enums.CouponsCategory;



public interface ICouponsDao extends CrudRepository<Coupon, Long>{
	
Coupon findByTitleAndCompanyId (String title, long companyId);

List <Coupon> findByCompanyId (Long companyId);

List <Coupon> findByCategory (CouponsCategory category);

Coupon findById(long couponId);

void deleteByExpriationDateLessThan (Date todayDate);



}

	





	


