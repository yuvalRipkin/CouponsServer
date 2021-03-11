 package com.yuval.coupons.dto;

import com.yuval.coupons.entities.User;

public class UserInfoDto {
	private String userName;
	private String firstName;
	private String lastName;

	public UserInfoDto(User user) {
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "UserInfoDto [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
