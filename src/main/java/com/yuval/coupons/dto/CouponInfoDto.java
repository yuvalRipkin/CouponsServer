package com.yuval.coupons.dto;

import java.util.Date;

import com.yuval.coupons.entities.Coupon;
import com.yuval.coupons.enums.Category;

public class CouponInfoDto {
	private long id;
	private String name;
	private float price;
	private String description;
	private Date endDate;
	private int amount;
	private String companyName;
	private Category category;
	private Long companyId;

	public CouponInfoDto() {

	}

	

	public CouponInfoDto(long id, String name, float price, String description, Date endDate, int amount,
			String companyName, Category category, Long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.endDate = endDate;
		this.amount = amount;
		this.companyName = companyName;
		this.category = category;
		this.companyId = companyId;
	}



	public CouponInfoDto(Coupon coupon) {
		this.id = coupon.getId();
		this.name = coupon.getName();
		this.price = coupon.getPrice();
		this.description = coupon.getDescription();
		this.endDate = coupon.getEndDate();
		this.companyName = coupon.getCompany().getName();
		this.amount = coupon.getAmount();
		this.category = coupon.getCategory();
		this.companyId = coupon.getCompany().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	

}
