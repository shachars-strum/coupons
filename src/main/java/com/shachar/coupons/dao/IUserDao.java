package com.shachar.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shachar.coupons.data.UserLoginData;
import com.shachar.coupons.entities.Coupon;
import com.shachar.coupons.entities.User;

import com.shachar.coupons.enums.ErrorTypes;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.utils.JdbcUtils;

@Repository
public interface IUserDao extends CrudRepository <User,Long>{

	User findByUserName (String userName);
	User findById(long userId);
	List<User> findByCompanyId(long companyId);



}
