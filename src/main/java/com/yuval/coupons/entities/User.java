package com.yuval.coupons.entities;

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
import com.yuval.coupons.dto.UserDto;
import com.yuval.coupons.enums.UserType;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false)
	private UserType userType;

	@JsonIgnore
	@ManyToOne
	private Company company;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;

	public User() {
	}
	
	public User(UserDto user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password =  user.getPassword();
		this.firstName =  user.getFirstName();
		this.lastName =  user.getLastName();
		this.userType = user.getUserType();
	}

	public User(long id, String userName, String password, String firstName, String lastName, UserType userType,
			Company company, List<Purchase> purchases) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.company = company;
		this.purchases = purchases;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
