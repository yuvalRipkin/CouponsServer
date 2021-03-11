package com.yuval.coupons.enums;

public enum ErrorType {

	GENERAL_ERROR(601, "General error", true), LOGIN_FAILED(603, "Login failed. Please try again.", false),
	NAME_ALREADY_EXISTS(604, "The name you chose already exists. Please enter another name", false),
	MUST_ENTER_NAME(605, "Can not insert an null/empty name", false),
	MUST_ENTER_ADDRESS(606, "Can not insert an null/empty address", false),
	ID_DOES_NOT_EXIST(607, "This ID does'nt exist", false),
//	NON_REPLACEABLE_NAME("Cannot change the name", false),
	INVALID_PASSWORD(608,
			"Password must contain at least 8 charaters, only UpperCase lettersand and at least one digit", false),
	NOT_ENOUGH_COUPONS_LEFT(609, "Not enough coupons left to purchase the amount requested", false),
	COUPON_EXPIERED(610, "The coupon is expiered", false),
	COUPON_TITLE_EXIST(611, "The title of this coupon is already exists, please change the title", false),
	INVALID_PRICE(612, "Coupon price must be more than 0", false),
	INVALID_EMAIL(613, "Email address is InValid, Please enter a valid email address", false),
	INVALID_AMOUNT(614, "Coupon's amount must be more than 0", false),
	INVALID_DATES(615, "The dates you've entered are wrong", false),
	MUST_INSERT_A_VALUE(617, "Must insert a value", false), FAIL_TO_GENERATE_ID(616, "Could not genetate id", false),
	COMPANY_CREATION_FAILED(618, "Failed to create a new company", false),
	USER_CREATION_FAILED(619, "Failed to create a new USER", false),
	COUPON_CREATION_FAILED(620, "Failed to create a new COUPON", false),
	PURCHASE_CREATION_FAILED(621, "Failed to create a new PURCHASE", false),
	USER_DELETE_FAILED(622, "Failed to delete user", false),
	COMPANY_DELETE_FAILED(623, "Failed to delete Company", false),
	COUPON_DELETE_FAILED(624, "Failed to delete Coupon", false),
	PURCHASE_DELETE_FAILED(625, "Failed to delete Purchase", false),
	USER_NAME_EXISTS(626, "User name is alredy taken", false);

	private int errorNumber;
	private String errorMessage;
	private boolean isShowStackTrace;

	private ErrorType(int errorNumber, String internalMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
		this.isShowStackTrace = isShowStackTrace;
	}

	private ErrorType(int errorNumber, String internalMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

}
