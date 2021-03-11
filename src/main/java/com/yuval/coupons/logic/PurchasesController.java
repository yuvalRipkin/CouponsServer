package com.yuval.coupons.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yuval.coupons.dao.IPurchaseDao;
import com.yuval.coupons.dto.PurchaseDto;
import com.yuval.coupons.dto.PurchaseInfoDto;
import com.yuval.coupons.entities.Coupon;
import com.yuval.coupons.entities.Purchase;
import com.yuval.coupons.entities.User;
import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.exceptions.ApplicationException;

@Controller
public class PurchasesController {

	@Autowired
	private IPurchaseDao purchaseDao;

	@Autowired
	private CouponsController couponsController;

	@Autowired
	private UsersController usersController;

	public PurchasesController() {
		super();

	}

	public long addPurchase(PurchaseDto purchase) throws ApplicationException {
		System.out.println("&&&&&&&&&&");
		System.out.println(purchase);
		Coupon couponEntity = couponsController.getEntityCoupon(purchase.getCouponId());
		validatePurchase(couponEntity, purchase);
		try {
			Purchase purchaseEntity = new Purchase(purchase);
			User user = this.usersController.getUserEntity(purchase.getUserId());
			purchaseEntity.setUser(user);
			purchaseEntity.setCoupon(couponEntity);
			purchaseEntity.setTimestamp(Calendar.getInstance().getTime());
			long purchaseId = this.purchaseDao.save(purchaseEntity).getId();
			System.out.println("******************");
			System.out.println(purchase.getAmount());
			System.out.println();
			int amountCoupons = couponEntity.getAmount() - purchase.getAmount();

			couponsController.updateCouponAmount(amountCoupons, couponEntity.getId());
			return purchaseId;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.PURCHASE_CREATION_FAILED, "Purchase creation failed");
		}

	}

	public void deletePurchase(long id) throws ApplicationException {
		try {
			this.purchaseDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.PURCHASE_DELETE_FAILED, "Purchase delte failed");
		}
	}

	public PurchaseInfoDto getPurchase(long id) throws ApplicationException {
		try {
			Purchase purchase = this.purchaseDao.findById(id).get();
			PurchaseInfoDto dto = new PurchaseInfoDto(purchase);
			return dto;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get purchase");
		}
	}

	public List<PurchaseInfoDto> getAllPurchases() throws ApplicationException {
		try {
			return this.purchaseDao.getAllPurchases();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get all Purchases");
		}
	}

	public List<PurchaseInfoDto> getAllPurchasesByUser(long userId) throws ApplicationException {
		try {
			List<PurchaseInfoDto> purchases = this.purchaseDao.getAllPurchasesByUser(userId);
			return purchases;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get all purchases");
		}
	}

	public List<PurchaseInfoDto> getAllCompanyPurchases(long companyId) throws ApplicationException {
		try {
			List<PurchaseInfoDto> purchases = this.purchaseDao.getAllCompanyPurchases(companyId);
			return purchases;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get all purchases");
		}
	}

	public void deletePurchasesOfExpiredCoupons(Date endDate) throws ApplicationException {
		try {
			purchaseDao.deletePurchasesOfExpiredCoupons(endDate);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"Failed to delete purchases of expired coupons for date: " + endDate.getTime());
		}
	}

	private void validatePurchase(Coupon coupon, PurchaseDto purchase) throws ApplicationException {
		if (coupon.getAmount() == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "No coupons left :(");
		}
		if (coupon.getAmount() < purchase.getAmount()) {
			throw new ApplicationException(ErrorType.NOT_ENOUGH_COUPONS_LEFT,
					ErrorType.NOT_ENOUGH_COUPONS_LEFT.getErrorMessage());
		}
	}

}
