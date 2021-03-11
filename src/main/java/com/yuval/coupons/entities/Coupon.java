package com.yuval.coupons.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuval.coupons.dto.CouponDto;
import com.yuval.coupons.enums.Category;

@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "price", nullable = false)
	private float price;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
	private Category category;

	@Column(name = "amount", nullable = false)
	private int amount;

	@JsonIgnore
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	// @JoinColumn(name = "coupon_id", nullable = false)
	private List<Purchase> purchases;

	@JsonIgnore
	@ManyToOne
	private Company company;

	public Coupon() {

	}

	public Coupon(long id, String name, float price, String description, Date startDate, Date endDate,
			Category category, int amount, List<Purchase> purchases, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.amount = amount;
		this.purchases = purchases;
		this.company = company;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", category=" + category + ", amount=" + amount
				+ "]";
	}

	public Coupon(CouponDto couponDto) {
		this.id = couponDto.getId();
		this.name = couponDto.getName();
		this.price = couponDto.getPrice();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.category = couponDto.getCategory();
		this.amount = couponDto.getAmount();
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

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
