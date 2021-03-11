package com.yuval.coupons.dto;

import com.yuval.coupons.entities.User;
import com.yuval.coupons.enums.UserType;

public class UserDto {
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private UserType userType;
	private Long companyId;

	public UserDto() {
	}

	// Constructor for tests
	public UserDto(String userName, String password, String firstName, String lastName, UserType userType,
			Long companyId) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.companyId = companyId;
	}

	// Constructor for DB extractions
	public UserDto(long id, String userName, String password, String firstName, String lastName, UserType userType,
			Long companyId) {

		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.companyId = companyId;
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userType = user.getUserType();

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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userType=" + userType + ", companyId=" + companyId + "]";
	}

	
}
