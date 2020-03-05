package com.shachar.coupons.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.shachar.coupons.entities.Customer;

@Repository
public interface ICustomerDao extends CrudRepository<Customer, Long> {

	Customer findById(long customerId);
}
