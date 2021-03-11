package com.yuval.coupons.timerTask;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.exceptions.ApplicationException;
import com.yuval.coupons.logic.CouponsController;
import com.yuval.coupons.logic.PurchasesController;

@Component
public class TimerTask {

	@Autowired
	private CouponsController couponsController;

	@Autowired
	private PurchasesController purchasesController;

	@PostConstruct
	@Scheduled(cron = " 0 * * ? * *")
	public void deleteExpiredCoupons() throws ApplicationException {
		try {
			long timeInMillis = System.currentTimeMillis();
			Date currentDate = new Date(timeInMillis);
			System.out.println("Coupon delete started");
			purchasesController.deletePurchasesOfExpiredCoupons(currentDate);
			this.couponsController.deleteExpiredCoupons(currentDate);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_DELETE_FAILED, "failed to delete expired coupons");
		}
	}
}
