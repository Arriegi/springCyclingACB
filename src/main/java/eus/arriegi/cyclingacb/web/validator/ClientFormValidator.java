package eus.arriegi.cyclingacb.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Client;

@Component
public class ClientFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	public boolean supports(Class<?> clazz) {
		return Client.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Client client = (Client) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.client.name");
		if(client.getEmail() != null && client.getEmail().length() > 0 && !emailValidator.valid(client.getEmail())){
			errors.rejectValue("email", "Pattern.client.email");
		}
	}

}
