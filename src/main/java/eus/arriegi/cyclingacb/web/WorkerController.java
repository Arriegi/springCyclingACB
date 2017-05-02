package eus.arriegi.cyclingacb.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import eus.arriegi.cyclingacb.domain.Worker;
import eus.arriegi.cyclingacb.domain.authentication.Profile;
import eus.arriegi.cyclingacb.domain.authentication.Role;
import eus.arriegi.cyclingacb.service.RoleManager;
import eus.arriegi.cyclingacb.service.SectionManager;
import eus.arriegi.cyclingacb.service.WorkerManager;
import eus.arriegi.cyclingacb.utils.reports.ColumnData;
import eus.arriegi.cyclingacb.utils.reports.ReportUtils;
import eus.arriegi.cyclingacb.web.validator.WorkerFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

@Controller
public class WorkerController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private WorkerManager workerManager;

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private SectionManager sectionManager;

	@Autowired
	private WorkerFormValidator workerFormValidator;

	@InitBinder("worker")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(workerFormValidator);
		binder.registerCustomEditor(Section.class, "section", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Section section = sectionManager.getSection(Long.parseLong(text));
				setValue(section);
			}
		});
		binder.registerCustomEditor(Role.class, "role", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Role role = roleManager.getRole(Long.parseLong(text));
				setValue(role);
			}
		});
	}

	@RequestMapping(value = "/workers.html")
	public ModelAndView workers() throws ServletException, IOException {
		return new ModelAndView("workers", "workers", workerManager.getWorkers());
	}

	@RequestMapping(value = "/newWorker.html", method = RequestMethod.GET)
	public ModelAndView newWorker(Model model) throws ServletException, IOException {
		ModelAndView mAndV = new ModelAndView("newWorker", "worker", new Worker());
		mAndV.addObject("sections", sectionManager.getSections());
		mAndV.addObject("roles", roleManager.getRoles());
		return mAndV;
	}

	@RequestMapping(value = "/editWorker.html", method = RequestMethod.GET)
	public ModelAndView editWorker(@RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView mAndV = new ModelAndView("newWorker", "worker", workerManager.getWorker(id));
			mAndV.addObject("sections", sectionManager.getSections());
			mAndV.addObject("roles", roleManager.getRoles());
			return mAndV;
		} else {
			return new ModelAndView("redirect:newWorker.html");
		}
	}

	@RequestMapping(value = "/deleteWorker.html")
	public ModelAndView deleteWorker(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			workerManager.removeWorker(id);
		}
		return new ModelAndView("redirect:workers.html");
	}

	@RequestMapping(value = "/newWorker.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateWorker(@ModelAttribute("worker") @Validated Worker worker, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			ModelAndView mAndV = new ModelAndView("newWorker", "worker", worker);
			mAndV.addObject("sections", sectionManager.getSections());
			mAndV.addObject("roles", roleManager.getRoles());
			return mAndV;
		} else {
			worker.setPassword(passwordEncoder.encode(worker.getPassword()));
			if (worker.getId() != null && worker.getId() > 0) {
				workerManager.updateWorker(worker);
			} else {
				workerManager.addWorker(worker);
			}
			return new ModelAndView("redirect:workers.html");
		}
	}

	// ========================= PROFILE ============================== //

	@RequestMapping(value = "/profile.html", method = RequestMethod.GET)
	public ModelAndView seeProfile() throws ServletException, IOException {
		return new ModelAndView("seeProfile", "profile", new Profile(getLoggedWorker()));
	}

	@RequestMapping(value = "/editProfile.html", method = RequestMethod.POST)
	public ModelAndView editProfile(HttpServletRequest request, @ModelAttribute("profile") @Validated Profile profile,
			BindingResult result) throws ServletException, IOException {
		if (result.hasErrors()) {
			return new ModelAndView("newWorker", "profile", profile);
		} else {
			Worker worker = getLoggedWorker();
			worker.setEmail(profile.getEmail());
			worker.setPassword(passwordEncoder.encode(profile.getPassword()));
			workerManager.updateWorker(worker);
			if (!worker.getEmail().equals(profile.getEmail())) {
				new SecurityContextLogoutHandler().logout(request, null, null);
				return new ModelAndView("redirect:login");
			} else {
				return new ModelAndView("seeProfile", "profile", worker);
			}
		}
	}

	// =================== REPORTS =====================//
	@RequestMapping(value = "/workersReport.pdf", method = RequestMethod.GET)
	public void getClientsPdf(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		try {
			JasperReportBuilder jrb = createJasperReport();
			jrb.toPdf(out);
		} catch (DRException e) {
			throw new ServletException(e);
		}
		out.close();
	}

	@Override
	protected JasperReportBuilder createJasperReport() {
		ReportUtils<Worker> reportUtils = new ReportUtils<>("Trabajadores", null, workerManager.getWorkers());
		List<ColumnData> columns = new ArrayList<ColumnData>();
		ColumnData colData = new ColumnData("Trabajador", "fullName", "string");
		columns.add(colData);
		colData = new ColumnData("Sección", "section.name", "string");
		columns.add(colData);
		return reportUtils.getBuilder(columns);
	}
}
