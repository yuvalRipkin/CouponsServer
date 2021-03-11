package com.yuval.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yuval.coupons.dto.CompanyDto;
import com.yuval.coupons.entities.Company;

public interface ICompanyDao extends CrudRepository<Company, Long> {

	@Query("SELECT new com.yuval.coupons.dto.CompanyDto(c) from Company c")
	public List<CompanyDto> getAllCompanies();

	public boolean existsByPhoneNumber(String phoneNumber);

	public boolean existsByName(String name);
}
