package com.yuval.coupons.dto;

import com.yuval.coupons.enums.UserType;

public class UserLoginDetails {

	private String userName;
	private String password;
	private UserType userType;


	public UserLoginDetails() {

	}

	public UserLoginDetails(String userName, String password, UserType userType) {
		super();
		this.userName = userName;
		this.password = String.valueOf(password.hashCode());
		this.userType = userType;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	@Override
	public String toString() {
		return "UserLoginDetails [userName=" + userName + ", password=" + password + ", userType=" + userType + "]";
	}

}
