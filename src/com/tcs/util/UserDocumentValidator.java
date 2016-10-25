package com.tcs.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcs.model.UserDocument;

@Component
public class UserDocumentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDocument.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		UserDocument document = (UserDocument) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentName", "", "Please enter the Document Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Please enter the Document Type");
		if(document.getDescription().length() > 50)
		{
			errors.rejectValue("description", "", "The document description should be less than 50 characters");
		}
	}

}
