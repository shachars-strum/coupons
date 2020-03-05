package com.shachar.coupons.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shachar.coupons.entities.Company;
import com.shachar.coupons.exceptions.ApplicationException;
import com.shachar.coupons.logic.CompanyController;


@RestController
@RequestMapping("/companies")
public class CompanyApi {

	@Autowired
	private CompanyController companiesController;
	
	@PostMapping
	public long createCompany(@RequestBody Company company) throws ApplicationException  {
		return companiesController.createCompany(company);
	}
	
	@PutMapping("{companyId}")
	public void updateCompany (@RequestBody Company company) throws ApplicationException {
		companiesController.updateCompany(company);
	}
	
	@GetMapping ("{companyId}")
	public Company getCompany (@PathVariable ("companyId") long companyId) throws ApplicationException {
		return companiesController.getCompanyById(companyId);
	}
	
	@GetMapping()
	public List<Company> getAllCompanies () throws ApplicationException{
		return companiesController.getAllCompanies();
	}
	
	@DeleteMapping("{companyId}")
	public void deleteCompany (@PathVariable("companyId") long companyId) throws ApplicationException {
		companiesController.deleteCompany(companyId);
	}
}
