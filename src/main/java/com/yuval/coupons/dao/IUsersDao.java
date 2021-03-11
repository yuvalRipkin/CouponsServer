package com.yuval.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yuval.coupons.dto.UserDto;
import com.yuval.coupons.dto.UserInfoDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.entities.User;

@Repository
public interface IUsersDao extends CrudRepository<User, Long> {

	@Query("SELECT new com.yuval.coupons.dto.UserLoginData(u) from User u where u.userName= :userName and u.password= :password")
	public UserLoginData login(@Param("userName") String userName, @Param("password") String password);

	@Query("SELECT new com.yuval.coupons.dto.UserDto(u) from User u")
	public List<UserDto> getAllUsers();

	@Query("SELECT new com.yuval.coupons.dto.UserInfoDto(u) from User u where u.id =userID")
	public UserInfoDto getUser(@Param("userID") long userId);
	
	public boolean existsByUserName(String userName);

}
