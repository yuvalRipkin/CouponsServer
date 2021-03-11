package com.yuval.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yuval.coupons.dao.IUsersDao;
import com.yuval.coupons.dto.SuccessfulLoginData;
import com.yuval.coupons.dto.UserDto;
import com.yuval.coupons.dto.UserInfoDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.dto.UserLoginDetails;
import com.yuval.coupons.entities.Company;
import com.yuval.coupons.entities.User;
import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.enums.UserType;
import com.yuval.coupons.exceptions.ApplicationException;

@Controller
public class UsersController {

	@Autowired
	private IUsersDao usersDao;

	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private CacheController cacheController;

	static final String TXT = "AJSKDLK#4%5IXM-2849$^^590AUHJLKJGFDl;AAds";

	public UsersController() {
		super();
	}

	public long createUser(UserDto userDto, UserLoginData data) throws ApplicationException {
		try {
			UserType type = userDto.getUserType();
			if (type == null) {
				userDto.setUserType(UserType.CUSTOMER);
			}
			isUsernameExist(userDto.getUserName());
			validateCreateUser(userDto);
			userDto.setPassword(String.valueOf(userDto.getPassword().hashCode()));
			User user = new User(userDto);

			if (userDto.getUserType().equals(UserType.COMPANY)) {
				Company company = new Company(companiesController.getCompany(userDto.getCompanyId()));
				user.setCompany(company);
			}

			long userID = usersDao.save(user).getId();
			return userID;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.USER_CREATION_FAILED, "Failed to create user " + userDto);
		}
	}

	public List<UserDto> getAllUsers() throws ApplicationException {
		try {
			List<UserDto> users = this.usersDao.getAllUsers();
			return users;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get all users");
		}
	}

	public void deleteUser(Long id, UserLoginData data) throws ApplicationException {
		try {
			if (data.getUserId() == id || data.getUserType().name().equals("ADMIN")) {
				usersDao.deleteById(id);
			} else {
				throw new ApplicationException(ErrorType.USER_DELETE_FAILED, "Not Allowed to do it, hacking attempt.");
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.USER_DELETE_FAILED,
					ErrorType.USER_DELETE_FAILED.getErrorMessage());
		}
	}

	public void updateUser(UserDto userDto, UserLoginData data) throws ApplicationException {
		try {
			validateCreateUser(userDto);

			User user = new User(userDto);
			user.setId(data.getUserId());
			this.usersDao.save(user);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Update user failed");
		}
	}

	public UserInfoDto getUser(long id) throws ApplicationException {
		try {

			UserInfoDto user = usersDao.getUser(id);

			return user;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to get user for user id: " + id);
		}

	}

	public User getUserEntity(long id) throws ApplicationException {
		try {
			return usersDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get user entity for user id: " + id);
		}
	}

	public SuccessfulLoginData login(UserLoginDetails details) throws ApplicationException {
		try {
			UserLoginData data = usersDao.login(details.getUserName(),
					String.valueOf(details.getPassword().hashCode()));

			if (data == null) {
				throw new ApplicationException(ErrorType.LOGIN_FAILED, "User login datais null." + data);
			}
			// data.setCompanyId(this.getUser(data.getUserId()).getCompanyId());
			System.out.println(data);
			String token = generateToken(details);

			cacheController.put(token, data);
			return new SuccessfulLoginData(token, data.getUserType());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.LOGIN_FAILED, "Login failed.");
		}

	}

	// Private methods
	private String generateToken(UserLoginDetails details) throws ApplicationException {
		int token = (details.getUserName() + Calendar.getInstance().getTime() + TXT).hashCode();
		return String.valueOf(token);
	}

	private void validateCreateUser(UserDto userDto) throws ApplicationException {

		if (userDto.getUserName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, "Name is null");
		}

		if (userDto.getUserName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, "Name is empty");
		}

		if (userDto.getUserName().length() < 2) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "User name is too short");
		}
		if ((userDto.getUserType().name().equals("COMPANY")) && (userDto.getCompanyId() == null)) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Companies must have company id");
		}
	}

	private void isUsernameExist(String userName) throws ApplicationException {
		if (this.usersDao.existsByUserName(userName)) {
			throw new ApplicationException(ErrorType.USER_NAME_EXISTS, "User name is alredy exist");
		}
	}

}
