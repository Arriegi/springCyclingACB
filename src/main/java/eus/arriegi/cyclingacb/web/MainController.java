package eus.arriegi.cyclingacb.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return "redirect:parts.html";
		} else {
			return "redirect:login";
		}
	}

}
