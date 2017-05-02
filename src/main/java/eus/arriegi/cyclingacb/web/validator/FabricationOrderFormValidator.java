package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.FabricationOrder;

@Component
public class FabricationOrderFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FabricationOrder.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.operation.name");
	}

}
