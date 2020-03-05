package com.shachar.coupons;
import java.sql.Date;
import java.util.Calendar;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shachar.coupons.logic.CouponController;


@Component
public class TimerTa extends TimerTask {
	
	@Autowired
	private CouponController couponsController;

	@Override
	public void run() {
		long now = Calendar.getInstance().getTimeInMillis();
		Date todayDate = new Date(now);
		try {
			couponsController.removeNotValidCouponsByEndDate(todayDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
