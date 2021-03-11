package com.yuval.coupons.api;

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

import com.yuval.coupons.dto.CouponDto;
import com.yuval.coupons.dto.CouponInfoDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.enums.Category;
import com.yuval.coupons.exceptions.ApplicationException;
import com.yuval.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {
	@Autowired
	private CouponsController couponsController;

	@PostMapping
	public long createCoupon(@RequestBody CouponDto coupon, HttpServletRequest request) throws ApplicationException {
		// Collecting the companyId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		coupon.setCompanyId(data.getCompanyId());
		long couponID = this.couponsController.createCoupon(coupon);
		return couponID;
	}

	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id) throws ApplicationException {
		this.couponsController.deleteCoupon(id);
	}

	@PutMapping
	public void updateCoupon(@RequestBody CouponDto coupon, HttpServletRequest request) throws ApplicationException {
		// Collecting the companyId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		coupon.setCompanyId(data.getCompanyId());
		this.couponsController.createCoupon(coupon);

	}

	@GetMapping("/{couponID}")
	public CouponInfoDto getCoupon(@PathVariable("couponID") Long id) throws ApplicationException {
		CouponInfoDto coupon = this.couponsController.getCoupon(id);
		return coupon;
	}

	@GetMapping
	public List<CouponInfoDto> getAllCoupons() throws ApplicationException {
		List<CouponInfoDto> coupons = this.couponsController.getAllCoupons();
		return coupons;
	}

	@GetMapping("/byCompanyId")
	public List<CouponInfoDto> getCouponsById(@RequestParam("companyId") long id) throws ApplicationException {
		List<CouponInfoDto> coupons = this.couponsController.getCouponsByCompanyId(id);

		return coupons;

	}

	@GetMapping("/byCategory")
	public List<CouponInfoDto> getCouponsByCategory(@RequestParam("category") Category category)
			throws ApplicationException {
		List<CouponInfoDto> coupons = this.couponsController.getCouponsByCategory(category);
		return coupons;
	}

	@GetMapping("/byMaxPrice")
	public List<CouponInfoDto> getAllPurchasedCouponsByMaxPrice(HttpServletRequest request,
			@RequestParam("maxPrice") float maxPrice) throws ApplicationException {
		// Collecting the userId from the cache in order to avoid hacking attempts.
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		List<CouponInfoDto> coupons = this.couponsController.getAllPurchasedCouponsByMaxPrice(maxPrice,
				userLoginData.getUserId());
		return coupons;
	}

}