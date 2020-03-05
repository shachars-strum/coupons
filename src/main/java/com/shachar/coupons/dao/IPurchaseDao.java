package com.shachar.coupons.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shachar.coupons.entities.Purchase;

public interface IPurchaseDao extends CrudRepository<Purchase, Long> {
	
	Purchase findById(long purchaseId);

	List<Purchase> findByCustomerId(Long customerId);

}












