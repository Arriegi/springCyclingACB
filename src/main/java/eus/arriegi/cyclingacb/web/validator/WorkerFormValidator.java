package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Worker;

@Component
public class WorkerFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Worker.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Worker worker = (Worker) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.worker.name");
		if (worker.getSection() == null) {
			errors.rejectValue("section", "NotNull.worker.section");
		}
		if (worker.getRole() == null) {
			errors.rejectValue("role", "NotNull.worker.role");
		}
	}

}
