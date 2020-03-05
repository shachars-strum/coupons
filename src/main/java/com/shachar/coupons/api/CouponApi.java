package com.shachar.coupons.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shachar.coupons.data.UserLoginData;
import com.shachar.coupons.entities.Coupon;
import com.shachar.coupons.enums.CouponsCategory;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.logic.CouponController;



@RestController
@RequestMapping("/coupons")
public class CouponApi {

	@Autowired
	private CouponController couponsController;
	
	@PostMapping
	public long createCoupon (@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(coupon);
		return couponsController.createCoupon(coupon, userData.getCompanyId());
	}
	
	@PutMapping("{couponId}")
	public void updateCoupon (@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		couponsController.updateCoupon(coupon,userData.getCompanyId(), userData.getUserType());
	}
	
	@GetMapping ("{couponId}")
	public Coupon getCoupon (@PathVariable ("couponId") long couponId) throws ApplicationException {
		return couponsController.getCoupon(couponId);
	}
	
	@GetMapping()
	public List<Coupon> getAllCoupons () throws ApplicationException{
		return couponsController.getAllCoupons();
	}
	
	@DeleteMapping("{couponId}")
	public void deleteCoupon (@PathVariable("couponId") long couponId, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		System.out.println("#################################################");
		couponsController.deleteCoupon(couponId, userData.getCompanyId(), userData.getUserType());
	}

	@GetMapping ("/byCompany")
	public List<Coupon> getCouponsByCompanyId (HttpServletRequest request) throws ApplicationException{
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Long companyId = userLoginData.getCompanyId();
		System.out.println(companyId+"!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return couponsController.getCouponsByCompanyId(companyId);
	}
	
	@GetMapping ("/byCategory")
	public List<Coupon> getCouponsByCategory (@RequestParam ("category") CouponsCategory category) throws ApplicationException{
		return couponsController.getCouponsByCategory(category);
	}
			

}

