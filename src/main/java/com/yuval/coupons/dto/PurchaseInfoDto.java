package com.yuval.coupons.dto;

import com.yuval.coupons.entities.Purchase;

public class PurchaseInfoDto {
	private long id;
	private long couponId;
	private int amount;
	private String couponName;
	private String description;

	public PurchaseInfoDto() {

	}

	public PurchaseInfoDto(long id, long couponId, int amount, String couponName, String description) {
		super();
		this.id = id;
		this.couponId = couponId;
		this.amount = amount;
		this.couponName = couponName;
		this.description = description;
	}

	public PurchaseInfoDto(Purchase purchase) {
		this.id = purchase.getId();
		this.couponId = purchase.getCoupon().getId();
		this.amount = purchase.getAmount();
		this.couponName = purchase.getCoupon().getName();
		this.description = purchase.getCoupon().getDescription();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
