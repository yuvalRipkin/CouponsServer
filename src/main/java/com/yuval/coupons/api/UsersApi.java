package com.yuval.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuval.coupons.dto.SuccessfulLoginData;
import com.yuval.coupons.dto.UserDto;
import com.yuval.coupons.dto.UserInfoDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.dto.UserLoginDetails;
import com.yuval.coupons.exceptions.ApplicationException;
import com.yuval.coupons.logic.UsersController;

@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UsersController userController;

	@PostMapping
	public long createUser(@RequestBody UserDto userDto, HttpServletRequest request) throws ApplicationException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		long userId = this.userController.createUser(userDto, data);
		return userId;
	}

	// In order to avoid of hacking attempts, I send the User data from the cache to
	// the controller and check if he try to delete a user with a different id from
	// the user's id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long id, HttpServletRequest request) throws ApplicationException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		this.userController.deleteUser(id, data);
	}

	@GetMapping
	public List<UserDto> getallUsers() throws ApplicationException {
		List<UserDto> users = this.userController.getAllUsers();
		return users;
	}

	@GetMapping("/{userId}")
	public UserInfoDto getUser(HttpServletRequest request) throws ApplicationException {
		// Collecting the userId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		long id = data.getUserId();
		UserInfoDto user = this.userController.getUser(id);
		return user;
	}

	@PutMapping
	public void updateUser(@RequestBody UserDto userDto, HttpServletRequest request) throws ApplicationException {
		// Collecting the userId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		this.userController.updateUser(userDto, data);
	}

	@PostMapping("/loginDetails")
	public SuccessfulLoginData login(@RequestBody UserLoginDetails details) throws ApplicationException {
		SuccessfulLoginData data = this.userController.login(details);
		return data;
	}

}
