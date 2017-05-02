package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.WorkOperation;

@Component
public class WorkOperationFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return WorkOperation.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		//WorkOperation wOp = (WorkOperation) target;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.worker.name");
	}

}
