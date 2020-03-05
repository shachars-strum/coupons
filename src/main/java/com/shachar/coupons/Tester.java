//package com.shachar.coupons;
//
//
//import java.sql.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import com.shachar.coupons.entities.Company;
//import com.shachar.coupons.entities.Coupon;
//import com.shachar.coupons.entities.Customer;
//import com.shachar.coupons.entities.Purchase;
//import com.shachar.coupons.entities.User;
//import com.shachar.coupons.enums.CompanyType;
//import com.shachar.coupons.enums.CouponsCategory;
//import com.shachar.coupons.enums.UserType;
//import com.shachar.coupons.logic.CompanyController;
//import com.shachar.coupons.logic.CouponController;
//import com.shachar.coupons.logic.CustomerController;
//import com.shachar.coupons.logic.PurchaseController;
//import com.shachar.coupons.logic.UserController;
//
//public class Tester {
//
//
//
//	public static void main(String[] args) throws Exception {
//
//		CompanyController companyController = new CompanyController();
//		Company company = new Company("cofe cofe", "Tel Aviv", "03-9956585", CompanyType.restaurant);
//		//companyController.createCompany(company);
//		//companyController.deleteCompany(67);
//		//Company company1 = new Company(62,"aroma", "Tel Aviv", "03-9958585", CompanyType.restaurant);
//		//companyController.upDateCompany(company1);
//		//System.out.println(companyController.getCompanyByCompanyId(62));
//		//	System.out.println(companyController.getAllCompanies());
//		//	
//		//		----------------------------------------------------------------------------------
//
//
//		LocalDate localDate = LocalDate.now();
//
//		CouponController couponController = new CouponController();
//		Coupon coupon = new Coupon( 280f, "fish meal", 45678l, 10,Date.valueOf("2020-10-18"),CouponsCategory.restaurant, Date.valueOf("2019-10-18"));
//		//couponController.createCoupon(coupon);
//		//couponController.deleteCoupon(40);
//		//System.out.println(coupon.getCouponId());
////		Coupon coupon1 = new Coupon( 46,280f, "italien food", 2, Date.valueOf("2020-10-16"),CouponscCategory.restaurant, Date.valueOf("2019-10-17"));
////		couponController.upDateCoupon(coupon1);
//		//System.out.println(couponController.getCouponByCouponId(36));
//		//System.out.println(couponController.getAllCoupons());
//		//System.out.println(couponController.getCouponsByCategory(CouponscCategory.restaurant));
//		//System.out.println(couponController.getCompanyCoupons(45678));
//		System.out.println(couponController.getPurchasedCoupons());
//
//		//-----------------------------------------------------------------------------------
//
//		CustomerController customerController = new CustomerController();
//		Customer customer = new Customer ("i@gmail.com","Qwer124",UserType.customer,true, 2, Date.valueOf("1991-04-16"), "Modiin", "052848");		
//		//customerController.createCustomer(customer);
//		//customerController.deleteCustomer(16);
//		//Customer customer1 = new Customer ("galiii@gmail.com",new String("Qwer234").hashCode(),UserType.customer,18,true, 2, Date.valueOf("1991-04-16"), "haifa", "052848");		
//		//customerController.upDateCustomer(customer1);
//		//System.out.println(customerController.getCustomerByCustomerId(2));
//		//System.out.println(customerController.getAllCustomers());
//
//		//--------------------------------------------------------------------------------------
//
//		PurchaseController purchaseController = new PurchaseController();
//		Purchase purchase = new Purchase ( null, 48,  (long) 17, 3 );
//		//purchaseController.createPurchase(purchase);
//		//purchaseController.deletePurchase(5);
//		//Purchase purchase1 = new Purchase (6,Date.valueOf("2019-10-30"), 5 );
//		//purchaseController.updatePurchase(purchase1);
//		//System.out.println(purchaseController.getPurchasebyPurchaseId(6));
//		//System.out.println(purchaseController.getAllpurchases());
//
//		//-----------------------------------------------------------------------------------------
//
//		UserController userController = new UserController();
//		User user = new User("avi3211@gmail.com","Qwer1234", (long) 67 ,  UserType.company);
//		userController.createUser(user);
//		//userController.deleteUser(6);
////		User user1 = new User(18,"shachar@gmail.com",new String("Qwer1234").hashCode(), (long) 67 ,  UserType.company);
////		userController.upDateUser(user1);
//		//System.out.println(userController.getUserByUserId(1));
//		//System.out.println(userController.getAllUsers());
//	}
//
//
//
//
//
//}
