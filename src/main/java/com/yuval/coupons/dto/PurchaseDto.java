package com.yuval.coupons.dto;

import java.util.Calendar;
import java.util.Date;

import com.yuval.coupons.entities.Purchase;

public class PurchaseDto {

	private long id;
	private long userId;
	private long couponId;
	private int amount;
	private Date timestamp;

	public PurchaseDto() {
	}

	// Constructor for tests
	public PurchaseDto(long userId, long couponId, int amount, Date timestamp) {
		super();
		this.userId = userId;
		this.couponId = couponId;
		this.amount = amount;
		this.timestamp = Calendar.getInstance().getTime();
	}

	// Constructor for DB extractions
	public PurchaseDto(long id, long userId, long couponId, int amount, Date timestamp) {
		this(userId, couponId, amount, timestamp);
		this.id = id;

	}

	public PurchaseDto(Purchase purchase) {
		this.id = purchase.getId();
		this.userId = purchase.getUser().getId();
		this.couponId = purchase.getCoupon().getId();
		this.amount = purchase.getAmount();
		this.timestamp = purchase.getTimestamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", userId=" + userId + ", couponId=" + couponId + ", amount=" + amount
				+ ", timestamp=" + timestamp + "]";
	}

}
