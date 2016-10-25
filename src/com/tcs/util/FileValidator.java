package com.tcs.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tcs.model.FileBucket;

@Component
public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return FileBucket.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		FileBucket file = (FileBucket) obj;
		
		if(file.getMultipartFile() != null)
		{
			if(file.getMultipartFile().getSize() == 0)
			{
				errors.rejectValue("multipartFile", "", "The size of the selected file is 0. Please select an appropiate file.");
			}
		}
		else
		{
			errors.rejectValue("multipartFile", "", "Please select a file.");
		}

	}

}
