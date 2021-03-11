package com.yuval.coupons.dto;

import java.util.Date;

import com.yuval.coupons.entities.Coupon;
import com.yuval.coupons.enums.Category;

public class CouponDto {

	private long id;
	private String name;
	private float price;
	private String description;
	private Date startDate;
	private Date endDate;
	private Category category;
	private int amount;
	private long companyId;

	public CouponDto() {

	}

	public CouponDto(String name, float price, String description, Date startDate, Date endDate, Category category,
			long companyId, int amount) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.companyId = companyId;
		this.amount = amount;

	}

	public CouponDto(long id, String name, float price, String description, Date startDate, Date endDate,
			Category category, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.amount = amount;
	}

	// Constructor for DB extractions
	public CouponDto(long id, String name, float price, String description, Date startDate, Date endDate,
			Category category, int amount, long companyId) {
		this(name, price, description, startDate, endDate, category, companyId, amount);
		this.id = id;

	}

	public CouponDto(Coupon coupon) {
		this.id = coupon.getId();
		this.name = coupon.getName();
		this.price = coupon.getPrice();
		this.description = coupon.getDescription();
		this.startDate = coupon.getStartDate();
		this.endDate = coupon.getEndDate();
		this.category = coupon.getCategory();
		this.companyId = coupon.getCompany().getId();
		this.amount = coupon.getAmount();
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {

		this.companyId = companyId;

	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", price=" + price + ", startDate=" + startDate + ", endDate="
				+ endDate + ", category=" + category + ", amount=" + amount + ", CompanyId=" + companyId + "] \n";
	}

}
