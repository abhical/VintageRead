package com.example.ElectronicStore.Validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserImageNameValidator implements ConstraintValidator<ValidUserImageName, String> {

	private Logger logger = LoggerFactory.getLogger(UserImageNameValidator.class);

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Define your custom validation logic here
		// For example, check if the image name ends with a valid extension
		logger.info("Message from User Image Name Validation {} ", value);
		if (value == null || value.isEmpty()) {
			return false; // Consider empty value as in-valid, or adjust based on your needs
		}
		return value.matches("^.+\\.(jpg|jpeg|png)$"); // Simple regex to check for image file extensions
	}
}
