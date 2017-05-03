package eus.arriegi.cyclingacb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import eus.arriegi.cyclingacb.domain.Player;
import eus.arriegi.cyclingacb.service.PlayerManager;
import eus.arriegi.cyclingacb.web.validator.PlayerFormValidator;

@Controller
@SessionAttributes({ "loggedPlayer" })
public abstract class BaseController {

	@Autowired
	protected PlayerManager playerManager;
	@Autowired
	protected PlayerFormValidator playerFormValidator;
	
	@InitBinder("loggedPlayer")
	protected void initWorkerBinder(WebDataBinder binder) {
		binder.setValidator(playerFormValidator);
	}
	
	@ModelAttribute("loggedPlayer")
	public Player getLoggedPlayer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			Player player = playerManager.getPlayer(auth.getName());
			return player;
		} else {
			return null;
		}
	}
	
}
