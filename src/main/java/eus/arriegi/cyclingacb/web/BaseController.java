package eus.arriegi.cyclingacb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import eus.arriegi.cyclingacb.domain.Worker;
import eus.arriegi.cyclingacb.service.WorkerManager;
import eus.arriegi.cyclingacb.web.validator.WorkerFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

@Controller
@SessionAttributes({ "loggedWorker" })
public abstract class BaseController {

	@Autowired
	protected WorkerManager workerManager;
	@Autowired
	protected WorkerFormValidator workerFormValidator;
	
	@InitBinder("loggedWorker")
	protected void initWorkerBinder(WebDataBinder binder) {
		binder.setValidator(workerFormValidator);
	}
	
	@ModelAttribute("loggedWorker")
	public Worker getLoggedWorker() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			Worker worker = workerManager.getWorker(auth.getName());
			return worker;
		} else {
			return null;
		}
	}
	
	protected abstract JasperReportBuilder createJasperReport();
	
}
