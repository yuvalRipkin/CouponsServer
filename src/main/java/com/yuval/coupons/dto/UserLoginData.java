package com.yuval.coupons.dto;

import com.yuval.coupons.entities.User;
import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.enums.UserType;
import com.yuval.coupons.exceptions.ApplicationException;

public class UserLoginData {
	private Long userId;
	private UserType userType;
	private Long companyId;

	// The method getCompany() may cause an exception, because it interacts with the
	// DB (lazy fetch type), in order to to avoid a situation that exception has
	// invoked without being caught we use try&catch at the constructor.
	public UserLoginData(User user) throws ApplicationException {
		super();
		try {
			this.userId = user.getId();
			this.userType = user.getUserType();
			if (userType.equals(UserType.COMPANY)) {
				this.companyId = user.getCompany().getId();
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed to create userLoginData object for user: " + user);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
		return "UserLoginData [userId=" + userId + ", userType=" + userType + ", companyId=" + companyId + "]";
	}

}
