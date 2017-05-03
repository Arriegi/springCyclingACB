package eus.arriegi.cyclingacb.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.arriegi.cyclingacb.domain.Team;
import eus.arriegi.cyclingacb.domain.TeamName;
import eus.arriegi.cyclingacb.service.TeamManager;
import eus.arriegi.cyclingacb.web.validator.TeamFormValidator;

@Controller
@SessionAttributes("teamId")
public class TeamController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private TeamManager teamManager;

	@Autowired
	private TeamFormValidator teamFormValidator;

	@InitBinder("team")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(teamFormValidator);
	}
	
	@InitBinder("teamName")
	protected void initBinderTeamName(WebDataBinder binder) {
		//binder.setValidator(teamFormValidator);
	}

	@RequestMapping(value = "/teams.html")
	public ModelAndView teams(HttpSession session, HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView("teams", "teams", teamManager.getTeams());
	}

	@RequestMapping(value = "/newTeam.html", method = RequestMethod.GET)
	public ModelAndView newTeam() throws ServletException, IOException {
		ModelAndView model = new ModelAndView("newTeam", "team", new Team());
		return model;
	}

	@RequestMapping(value = "/editTeam.html", method = RequestMethod.GET)
	public ModelAndView editTeam(HttpSession session, @RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView model = new ModelAndView("newTeam", "team", teamManager.getTeam(id));
			session.setAttribute("teamId", id);
			return model;
		} else {
			return new ModelAndView("redirect:newTeam.html");
		}
	}

	@RequestMapping(value = "/deleteTeam.html")
	public ModelAndView deleteTeam(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			teamManager.removeTeam(id);
		}
		return new ModelAndView("redirect:teams.html");
	}

	@RequestMapping(value = "/newTeam.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateTeam(@ModelAttribute("team") @Validated Team team, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			ModelAndView mAndV = new ModelAndView("newTeam", "team", team);
			return mAndV;
		} else {
			if (team.getId() != null && team.getId() > 0) {
				teamManager.updateTeam(team);
			} else {
				teamManager.addTeam(team);
			}
			return new ModelAndView("redirect:teams.html");
		}
	}

	//============= TEAM NAME ==================== //
	
	@RequestMapping(value = "/newTeamName.html", method = RequestMethod.GET)
	public ModelAndView newTeamName(HttpSession session) throws ServletException, IOException {
		Team team = teamManager.getTeam((Long)session.getAttribute("teamId"));
		ModelAndView model = new ModelAndView("newTeamName", "team", team);
		model.addObject("teamName",new TeamName());
		return model;
	}
	
	@RequestMapping(value = "/newTeamName.html", method = RequestMethod.POST)
	public ModelAndView newTeamName(HttpSession session, @ModelAttribute("teamName") TeamName teamName, BindingResult result) throws ServletException, IOException {
		Team team = teamManager.getTeam((Long)session.getAttribute("teamId"));
		team.addName(teamName.getYear(), teamName.getName());
		teamManager.updateTeam(team);
		return new ModelAndView("redirect:editTeam.html?id=" + team.getId());
	}
	
}
