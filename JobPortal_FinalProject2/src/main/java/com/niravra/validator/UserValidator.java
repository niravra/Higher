package com.niravra.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.niravra.DAO.UserDAO;
import com.niravra.exception.UserException;
import com.niravra.pojo.Email;
import com.niravra.pojo.User;



public class UserValidator implements Validator {
//	@Autowired
//	@Qualifier("userDao")
//	UserDAO userDao;
	
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		
		System.out.println("The Uname is  : " +user.getUsername());
		String name = user.getUsername();
		String email = user.getEmail().getEmailAddress();
		UserDAO userdao = new UserDAO();
		
//		if (name.equals("gagan")){
//			errors.rejectValue("username","error.invalid.user", "username already taken");
//		}
		try {
			System.out.println("The Uname inside try is  : " +user.getUsername());
			User u = userdao.getUserName(name);
			if (u != null){
				errors.rejectValue("username", "error.invalid.user", "username already taken");
			}
			
			Email e = userdao.getByEmail(email);
			if (e !=null){
				errors.rejectValue("email.emailAddress", "error.invalid.email.emailAddress", "email address already exists");
			}
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception: " + e.getMessage());
			
			
		}
		
	}
}
