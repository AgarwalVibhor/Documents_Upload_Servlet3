package com.tcs.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcs.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "Please enter the First Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Please enter the Last Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssoId", "", "Please enter an SSO ID");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Please enter an Email ID");
		if(!user.getEmail().contains("@"))
		{
			errors.rejectValue("email", "", "Please enter a valid Email ID");
		}

	}

}
