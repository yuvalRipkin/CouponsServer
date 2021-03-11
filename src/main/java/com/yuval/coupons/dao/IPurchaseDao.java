package com.yuval.coupons.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuval.coupons.dto.PurchaseInfoDto;
import com.yuval.coupons.entities.Purchase;

@Repository
public interface IPurchaseDao extends CrudRepository<Purchase, Long> {

	@Query("SELECT new com.yuval.coupons.dto.PurchaseInfoDto(p) from Purchase p")
	public List<PurchaseInfoDto> getAllPurchases();

	@Query("SELECT new com.yuval.coupons.dto.PurchaseInfoDto(p) from Purchase p where p.user.id= :userID")
	public List<PurchaseInfoDto> getAllPurchasesByUser(@Param(value = "userID") Long userId);

	@Query("SELECT new com.yuval.coupons.dto.PurchaseInfoDto(p) from Purchase p where p.coupon.company.id= :companyID")
	public List<PurchaseInfoDto> getAllCompanyPurchases(@Param("companyID") long companyId);

	@Modifying
	@Transactional
	@Query("Delete from Purchase p where p.coupon.id in(select c.id from Coupon c where c.endDate <= :endDate)")
	public void deletePurchasesOfExpiredCoupons(@Param("endDate") Date endDate);
}
