package com.shachar.coupons.logic;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shachar.coupons.dao.IPurchaseDao;
import com.shachar.coupons.entities.Purchase;
import com.shachar.coupons.enums.ErrorTypes;
import com.shachar.coupons.exceptions.ApplicationException;

import sun.security.util.PropertyExpander.ExpandException;



@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purchasesDao;
	@Autowired
	private CustomerController customersController;
	@Autowired
	private CouponController couponsController;

	public long createPurchase(Purchase purchase, long customerId) throws ApplicationException {

		purchase.setCustomer(customersController.getCustomer(customerId));
		purchaseValitation(purchase);
		try {
			this.purchasesDao.save(purchase);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_PURCHASE, "Failed to create purchase");
		}
		updateAvailableQuantityOfCoupon(purchase);
		return purchase.getId();
	}

	private void purchaseValitation(Purchase purchase) throws ApplicationException {

		couponsController.isCouponExistsById(purchase.getCoupon().getId());
		customersController.isCustomerExistsById(purchase.getCustomer().getId());

		if (purchase.getAmountOfItems() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_ITEMS, "Quantity of items for purchase must be more than 0.");
		}
		int availableQuantity = couponsController.getAvailableQuantity(purchase.getCoupon().getId());
		if ((availableQuantity - purchase.getAmountOfItems()) < 0 ) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_ITEMS, "Not enough stock.");
		}

	}

	private void updateAvailableQuantityOfCoupon (Purchase purchase) throws ApplicationException {
		int amountBeforePurchase = couponsController.getAvailableQuantity(purchase.getCoupon().getId());
		int amountOfItemsForPerchase = purchase.getAmountOfItems();
		int amountAAfterThePurchase = amountBeforePurchase - amountOfItemsForPerchase;
		couponsController.updateAvailableQuantity(purchase.getCoupon().getId(), amountAAfterThePurchase);

	}

	public void updatePurchase(Purchase purchase) throws ApplicationException {
		this.purchaseValitation(purchase);
		try {
			this.purchasesDao.save(purchase);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_PURCHASE, "fail to update purchase.");
		}
	}

	public void deletePurchase(long purchaseId) throws ApplicationException {
		isPurchaseExistsById(purchaseId);
		try {
			this.purchasesDao.deleteById(purchaseId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_PURCHASE, "fail to delete purchase.");

		}
	}

	private void isPurchaseExistsById(long purchaseId) throws ApplicationException {
		if( this.purchasesDao.findById(purchaseId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_PURCHASE, "There is no purchase with this ID");
		}
	}



	public Purchase getPurchase(long purchaseId) throws ApplicationException {
		isPurchaseExistsById(purchaseId);
		try {
			return this.purchasesDao.findById(purchaseId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_PURCHASE, "fail to get purchase.");

		}
	}

	public List<Purchase> getAllPurchases() throws ApplicationException {
		try {
			return (List<Purchase>) this.purchasesDao.findAll();
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_PURCHASES, "fail to get purchases.");
		}
	}

	public List<Purchase> getAllSingleUserPurchases(Long customerId) throws ApplicationException {
try {
	return this.purchasesDao.findByCustomerId(customerId);
}catch(Exception e) {
	throw new ApplicationException(ErrorTypes.FAIL_TO_GET_PURCHASES, "fail to get purchases.");
}
	}


	
}


