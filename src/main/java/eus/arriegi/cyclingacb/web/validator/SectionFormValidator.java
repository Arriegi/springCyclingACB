package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Section;

@Component
public class SectionFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Section.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.section.name");
	}

}
