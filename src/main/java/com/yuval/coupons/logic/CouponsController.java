package com.yuval.coupons.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yuval.coupons.dao.ICouponsDao;
import com.yuval.coupons.dto.CompanyDto;
import com.yuval.coupons.dto.CouponDto;
import com.yuval.coupons.dto.CouponInfoDto;
import com.yuval.coupons.entities.Company;
import com.yuval.coupons.entities.Coupon;
import com.yuval.coupons.enums.Category;
import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.exceptions.ApplicationException;

@Controller
public class CouponsController {
	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private CompaniesController companiesController;

	public long createCoupon(CouponDto coupon) throws ApplicationException {
		try {
			isCouponNameExist(coupon.getName());
			validateCreateCoupon(coupon);
			Coupon couponEntity = new Coupon(coupon);
			Long companyId = coupon.getCompanyId();
			CompanyDto companyDto = this.companiesController.getCompany(companyId);
			couponEntity.setCompany(new Company(companyDto));
			long couponID = couponsDao.save(couponEntity).getId();
			return couponID;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_FAILED, "Failed to create coupon " + coupon);
		}
	}

	public void deleteCoupon(long id) throws ApplicationException {
		try {
			couponsDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_DELETE_FAILED, "failed to delete coupon: " + id);
		}
	}

	public void updateCoupon(CouponDto coupon) throws ApplicationException {
		try {
			validateCreateCoupon(coupon);
			Coupon couponEntity = new Coupon(coupon);

			Long companyId = coupon.getCompanyId();
			CompanyDto companyDto = this.companiesController.getCompany(companyId);
			couponEntity.setCompany(new Company(companyDto));
			couponsDao.save(couponEntity).getId();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to update coupon: " + coupon);
		}
	}

	public CouponInfoDto getCoupon(Long couponId) throws ApplicationException {
		try {
			Coupon coupon = this.couponsDao.findById(couponId).get();
			CouponInfoDto dto = new CouponInfoDto(coupon);
			return dto;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get coupon");
		}
	}

	public List<CouponInfoDto> getAllCoupons() throws ApplicationException {
		try {
			List<CouponInfoDto> coupons = this.couponsDao.getAllCoupons();
			return coupons;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get all coupons");
		}

	}

	public List<CouponInfoDto> getCouponsByCompanyId(long companyID) throws ApplicationException {
		try {
			return this.couponsDao.getCouponsByCompanyId(companyID);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"Failed to to get coupons by company id " + companyID);
		}
	}

	public List<CouponInfoDto> getCouponsByCategory(Category category) throws ApplicationException {
		try {

			return this.couponsDao.getCouponsByCategory(category);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"Failed to get coupons by category " + category.name());
		}

	}

	public List<CouponInfoDto> getAllPurchasedCouponsByMaxPrice(float maxPrice, long userId)
			throws ApplicationException {
		try {

			List<CouponInfoDto> coupons = this.couponsDao.getAllPurchasedCoupons(maxPrice, userId);
			return coupons;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed to get all purchased coupons by price " + maxPrice);
		}
	}

	public void deleteExpiredCoupons(Date endDate) throws ApplicationException {
		try {
			this.couponsDao.deleteExpiredCoupons(endDate);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"Failed to delete expired coupons fro date " + endDate);
		}
	}

	public Coupon getEntityCoupon(long id) throws ApplicationException {
		try {
			return this.couponsDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get antity coupon. coupon id: " + id);
		}
	}

	// private methods
	private void isCouponNameExist(String couponName) throws ApplicationException {

		if (this.couponsDao.existsByName(couponName)) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR,
					"coupon name: " + couponName + " is already exists.");
		}

	}

	public void updateCouponAmount(int amount, long couponID) throws ApplicationException {
		try {
			this.couponsDao.updateCouponAmount(amount, couponID);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed to update coupon amount, coupon id: " + couponID + " for " + amount + " coupons ");
		}
	}

	private void validateCreateCoupon(CouponDto coupon) throws ApplicationException {

		if (coupon.getDescription() == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "description of coupon cannot be null");
		}
		if (coupon.getDescription().isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "description of coupon cannot be empty");
		}
		if (coupon.getDescription().length() < 5) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR,
					"description length must be at least 5 characters.");
		}

		if (coupon.getStartDate().getTime() > coupon.getEndDate().getTime()) {
			throw new ApplicationException(ErrorType.INVALID_DATES,
					"The end date of the coupon cannot be earlier than the start date.");
		}
		if (coupon.getStartDate().getTime() < Calendar.getInstance().getTimeInMillis()) {
			throw new ApplicationException(ErrorType.INVALID_DATES,
					"The start date of the coupon cannot be earlier than the current date.");
		}
		if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Coupon amount cannot be less than one.");
		}
	}
}
