package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Material;

@Component
public class MaterialFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Material.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.material.name");
	}

}
