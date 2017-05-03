package eus.arriegi.cyclingacb.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.arriegi.cyclingacb.domain.Country;
import eus.arriegi.cyclingacb.domain.Cyclist;
import eus.arriegi.cyclingacb.service.CyclistManager;
import eus.arriegi.cyclingacb.web.validator.CyclistFormValidator;

@Controller
public class CyclistController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CyclistManager cyclistManager;

	@Autowired
	private CyclistFormValidator cyclistFormValidator;

	@InitBinder("cyclist")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(cyclistFormValidator);
		binder.registerCustomEditor(Country.class, "country", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Country country = cyclistManager.getCountry(Long.parseLong(text));
				setValue(country);
			}
		});
		binder.registerCustomEditor(Date.class, "birthdate", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				logger.info("Data formatua: " + text);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
	}

	@RequestMapping(value = "/cyclists.html")
	public ModelAndView cyclists(HttpSession session, HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView("cyclists", "cyclists", cyclistManager.getCyclists());
	}

	@RequestMapping(value = "/newCyclist.html", method = RequestMethod.GET)
	public ModelAndView newCyclist() throws ServletException, IOException {
		ModelAndView model = new ModelAndView("newCyclist", "cyclist", new Cyclist());
		model.addObject("countries",cyclistManager.getCountries());
		return model;
	}

	@RequestMapping(value = "/editCyclist.html", method = RequestMethod.GET)
	public ModelAndView editCyclist(@RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView model = new ModelAndView("newCyclist", "cyclist", cyclistManager.getCyclist(id));
			model.addObject("countries",cyclistManager.getCountries());
			return model;
		} else {
			return new ModelAndView("redirect:newCyclist.html");
		}
	}

	@RequestMapping(value = "/deleteCyclist.html")
	public ModelAndView deleteCyclist(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			cyclistManager.removeCyclist(id);
		}
		return new ModelAndView("redirect:cyclists.html");
	}

	@RequestMapping(value = "/newCyclist.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateCyclist(@ModelAttribute("cyclist") @Validated Cyclist cyclist, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			ModelAndView mAndV = new ModelAndView("newCyclist", "cyclist", cyclist);
			mAndV.addObject("countries",cyclistManager.getCountries());
			return mAndV;
		} else {
			if (cyclist.getId() != null && cyclist.getId() > 0) {
				cyclistManager.updateCyclist(cyclist);
			} else {
				cyclistManager.addCyclist(cyclist);
			}
			return new ModelAndView("redirect:cyclists.html");
		}
	}


}
