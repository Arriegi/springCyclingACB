package eus.arriegi.cyclingacb.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Team;

@Component
public class TeamFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Team.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Log logger = LogFactory.getLog(getClass());
		logger.warn(errors.getAllErrors());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "basicName", "NotEmpty.team.firstName");
	}

}
