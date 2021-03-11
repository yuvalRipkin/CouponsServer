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

import com.yuval.coupons.dto.CompanyDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.exceptions.ApplicationException;
import com.yuval.coupons.logic.CompaniesController;

@RestController
@RequestMapping("/companies")
public class CompaniesApi {
	@Autowired
	private CompaniesController companiesController;

	@PostMapping
	public long createCompany(@RequestBody CompanyDto company) throws ApplicationException {
		return this.companiesController.createCompany(company);
	}

	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long companyId, HttpServletRequest request)
			throws ApplicationException {
		// A full explanation is at the company controller.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");

		this.companiesController.deleteCompany(data, companyId);
	}

	@PutMapping("/{companyId}")
	public void updateCompany(@RequestBody CompanyDto company, HttpServletRequest request) throws ApplicationException {
		// Collecting the companyId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		company.setId(data.getCompanyId());
		this.companiesController.updateCompany(company);

	}

	@GetMapping
	public List<CompanyDto> getAllCompanies() throws ApplicationException {
		List<CompanyDto> companies = this.companiesController.getAllCompanies();

		return companies;
	}

	@GetMapping("/{companyId}")
	public CompanyDto getCompany(@PathVariable("companyId") long id) throws ApplicationException {
		CompanyDto company = this.companiesController.getCompany(id);

		return company;

	}

}
