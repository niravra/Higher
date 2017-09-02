package com.niravra.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.niravra.pojo.Company;



public class CompanyValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(Company.class);
	}

	public void validate(Object obj, Errors errors) {
		Company comp = (Company) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.comp", "Company Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyAddress", "error.invalid.comp", "Company Address Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyDescription", "error.invalid.comp", "Company Description Required");
		
		// check if user exists
		
	}
	
}
