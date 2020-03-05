package com.shachar.coupons.api;
import java.util.List;
import java.util.Optional;

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
import com.shachar.coupons.entities.Purchase;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.logic.PurchaseController;



@RestController
@RequestMapping("/purchases")
public class PurchaseApi {

	@Autowired
	private PurchaseController purchasesController;

	@PostMapping
	public long createPurchase (@RequestBody Purchase purchase, HttpServletRequest request) throws ApplicationException {

		System.out.println(purchase);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		return purchasesController.createPurchase(purchase, userData.getId());
	}

	@PutMapping
	public void updatePurchase (@RequestBody Purchase purchase) throws ApplicationException {
		purchasesController.updatePurchase(purchase);
	}

	@GetMapping ("{purchaseId}")
	public Purchase getPurchase (@PathVariable ("purchaseId") long purchaseId) throws ApplicationException {
		return purchasesController.getPurchase(purchaseId);
	}

	@GetMapping()
	public List<Purchase> getAllPurchases () throws ApplicationException{
		return purchasesController.getAllPurchases();
	}

	@DeleteMapping("{purchaseId}")
	public void deletePurchase (@PathVariable("purchaseId") long purchaseId) throws ApplicationException {
		purchasesController.deletePurchase(purchaseId);
	}

@GetMapping("/AllSingleUserPurchases")
public List<Purchase> getAllSingleUserPurchases(HttpServletRequest request) throws ApplicationException{
	UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
	Long customerId = userLoginData.getId();
			return purchasesController.getAllSingleUserPurchases(customerId);
}
	//	@GetMapping("/extended/ByPurchaseId")
	//	public PurchaseWithCouponInfo getPurchaseWithCouponInfo (@RequestParam ("purchaseId") long purchaseId) throws ApplicationException {
	//		return purchasesController.getPurchaseWithCouponInfo(purchaseId);
	//	}
}

