package com.yuval.coupons.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuval.coupons.dto.CouponInfoDto;
import com.yuval.coupons.entities.Coupon;
import com.yuval.coupons.enums.Category;

@Repository
public interface ICouponsDao extends CrudRepository<Coupon, Long> {

	// @Query("SELECT new com.yuval.coupons.dto.PurchaseInfoDto(p) from Purchase p
	// where p.coupon.company.id= :companyID")

	@Query("SELECT new com.yuval.coupons.dto.CouponInfoDto(c) from Coupon c where c.company.id= :companyId")
	public List<CouponInfoDto> getCouponsByCompanyId(@Param("companyId") long companyId);

	@Query("SELECT new com.yuval.coupons.dto.CouponInfoDto(c) from Coupon c where c.category= :category")
	public List<CouponInfoDto> getCouponsByCategory(@Param(value = "category") Category category);

	public boolean existsByName(String couponName);

	@Query("SELECT new com.yuval.coupons.dto.CouponInfoDto(c)from Coupon c left join c.purchases p where c.price< :maxPrice and p.user.id= :userID")
	public List<CouponInfoDto> getAllPurchasedCoupons(@Param("maxPrice") float maxPrice, @Param("userID") Long userId);

	@Query("SELECT new com.yuval.coupons.dto.CouponInfoDto(c) from Coupon c")
	public List<CouponInfoDto> getAllCoupons();

	@Transactional
	@Modifying
	@Query("update Coupon c set c.amount = :amount where c.id = :couponID")
	public void updateCouponAmount(@Param("amount") int amount, @Param("couponID") long couponID);

	@Transactional
	@Modifying
	@Query("Delete Coupon c where c.endDate <= :endDate")
	public void deleteExpiredCoupons(@Param("endDate") Date endDate);
}
