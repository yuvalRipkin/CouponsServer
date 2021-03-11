package com.yuval.coupons.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuval.coupons.dto.PurchaseDto;

@Entity
@Table(name = "purchases")
public class Purchase {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "timestamp", nullable = false)
	private Date timestamp;

	@JsonIgnore
	@ManyToOne
	private Coupon coupon;

	@JsonIgnore
	@ManyToOne
	private User user;

	public Purchase() {

	}

	public Purchase(long id, int amount, Date timestamp, Coupon coupon, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.coupon = coupon;
		this.user = user;
	}

	public Purchase(PurchaseDto purchase) {
		this.id = purchase.getId();
		this.amount = purchase.getAmount();
		this.timestamp = purchase.getTimestamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
