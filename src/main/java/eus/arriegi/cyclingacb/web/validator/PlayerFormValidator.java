package eus.arriegi.cyclingacb.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eus.arriegi.cyclingacb.domain.Player;

@Component
public class PlayerFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Player.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Player player = (Player) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.player.name");
		if (player.getRoles() == null) {
			errors.rejectValue("role", "NotNull.player.role");
		}
	}

}
