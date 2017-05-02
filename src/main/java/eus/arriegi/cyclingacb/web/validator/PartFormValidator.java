package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Part;

@Component
public class PartFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Part.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Part part = (Part) target;
		if (part.getDate() == null) {
			errors.rejectValue("date", "Pattern.part.date");
		}
		if (part.getWorker() == null) {
			errors.rejectValue("date", "Pattern.part.worker");
		}
	}

}
