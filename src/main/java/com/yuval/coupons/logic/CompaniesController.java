package com.yuval.coupons.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Controller;

import com.yuval.coupons.dao.ICompanyDao;
import com.yuval.coupons.dto.CompanyDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.entities.Company;
import com.yuval.coupons.enums.ErrorType;
import com.yuval.coupons.enums.UserType;
import com.yuval.coupons.exceptions.ApplicationException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompanyDao companiesDao;

	public CompaniesController() {
		super();

	}

	public long createCompany(CompanyDto company) throws ApplicationException {
		validateCreateCompany(company);
		validationsForCompany(company);
		try {

			Company companyEntity = new Company(company);
			this.companiesDao.save(companyEntity);
			long companyId = companyEntity.getId();
			return companyId;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COMPANY_CREATION_FAILED, "Failed to create company" + company);
		}
	}

	public void deleteCompany(UserLoginData data, long companyId) throws ApplicationException {
		try {
			/*
			 * In order to avoid hacking attempts and in order to avoid from past workers of
			 * any company which left thier job to delete the company account, I have
			 * decided that only an admin would be able to delete a company acount, so if
			 * the user is not an admin, we will throw an exception.
			 */
			if (!(data.getUserType().equals(UserType.ADMIN))) {
				throw new ApplicationContextException("Not allowed, might be a hacking attempt of user: " + data);
			}
			companiesDao.deleteById(data.getCompanyId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COMPANY_DELETE_FAILED,
					"Failed to delete company" + data + "\n" + companyId);
		}
	}

	public void updateCompany(CompanyDto company) throws ApplicationException {
		try {
			validationsForCompany(company);
			Company companyEntity = new Company(company);
			this.companiesDao.save(companyEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Failed to update company" + company);
		}
	}

	public List<CompanyDto> getAllCompanies() throws ApplicationException {
		try {
			return companiesDao.getAllCompanies();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get all compamnies");
		}

	}

	public CompanyDto getCompany(long id) throws ApplicationException {
		try {
			Company companyEntity = companiesDao.findById(id).get();
			CompanyDto companyDto = new CompanyDto(companyEntity);
			return companyDto;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to get company, company id: " + id);
		}

	}

	// Private Methods
	private void validateCreateCompany(CompanyDto company) throws ApplicationException {
		validationsForCompany(company);

		// check if there is a company with this name already
		if (isCompanyNameExist(company.getName())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Company name is already exist!");
		}

		if (isPhoneNumberExist(company.getPhoneNumber())) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Phone number is already exist");
		}
	}

	private void validationsForCompany(CompanyDto company) throws ApplicationException {

		// check if the company name longer then 2 characters and not null or empty.
		if (company.getName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, "Null company name");
		}
		if (company.getName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, "Empty company name");
		}
		if (company.getName().length() < 3) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Company name must be three characters at least");
		}
		// check if the phone-number is valid and not exist in the DB already.

		if (company.getPhoneNumber().length() < 9 || company.getPhoneNumber().length() > 11) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Invalid phone number");
		}
		if (!company.getPhoneNumber().startsWith("0") && !company.getPhoneNumber().startsWith("+")) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Invalid phone number");
		}

	}

	private boolean isPhoneNumberExist(String phoneNumber) throws ApplicationException {
		return companiesDao.existsByPhoneNumber(phoneNumber);
	}

	private boolean isCompanyNameExist(String name) throws ApplicationException {

		return companiesDao.existsByName(name);
	}

}
