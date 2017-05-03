package eus.arriegi.cyclingacb.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Cyclist;

@Component
public class CyclistFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Cyclist.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Log logger = LogFactory.getLog(getClass());
		logger.warn(errors.getAllErrors());
		Cyclist cyclist = (Cyclist) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.cyclist.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.cyclist.lastName");
		if (cyclist.getCountry() == null) {
			errors.rejectValue("country", "Pattern.cyclist.country");
		}
	}

}
