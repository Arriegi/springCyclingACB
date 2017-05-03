package eus.arriegi.cyclingacb.web;

import java.io.IOException;

import javax.servlet.ServletException;

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

import eus.arriegi.cyclingacb.domain.Section;
import eus.arriegi.cyclingacb.service.SectionManager;
import eus.arriegi.cyclingacb.web.validator.SectionFormValidator;

@Controller
public class SectionController extends BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SectionManager sectionManager;
	
	@Autowired
	private SectionFormValidator sectionFormValidator;
	
	@InitBinder("section")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(sectionFormValidator);
	}

	@RequestMapping(value = "/sections.html")
	public ModelAndView sections() throws ServletException, IOException {
		return new ModelAndView("sections", "sections", sectionManager.getSections());
	}
	
	@RequestMapping(value = "/newSection.html", method = RequestMethod.GET)
	public ModelAndView newSection()	throws ServletException, IOException {
		return new ModelAndView("newSection","section",new Section());
	}
	
	@RequestMapping(value = "/editSection.html", method = RequestMethod.GET)
	public ModelAndView editSection(@RequestParam("id") Long id)
			throws ServletException, IOException {
		if (id != null && id > 0) {
			return new ModelAndView("newSection","section",sectionManager.getSection(id));
		} else {
			return new ModelAndView("redirect:newSection.html");
		}
	}
	
	@RequestMapping(value = "/deleteSection.html")
	public ModelAndView deleteSection(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			sectionManager.removeSection(id);
		} 
		return new ModelAndView("redirect:sections.html");
	}
	
	@RequestMapping(value = "/newSection.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateSection(@ModelAttribute("sectionForm") @Validated Section section,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("newSection","section",section);
		} else {
			if (section.getId() != null && section.getId() > 0) {
				sectionManager.updateSection(section);
			} else {
				sectionManager.addSection(section);
			}
			return new ModelAndView("redirect:sections.html");
		}
	}
	
}
