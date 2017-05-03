package eus.arriegi.cyclingacb.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;
import eus.arriegi.cyclingacb.service.OperationManager;
import eus.arriegi.cyclingacb.service.OperationTypeManager;
import eus.arriegi.cyclingacb.web.validator.OperationFormValidator;

@Controller
public class OperationController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private OperationManager operationManager;
	@Autowired
	private OperationTypeManager operationTypeManager;
	@Autowired
	private OperationFormValidator operationFormValidator;

	@InitBinder("operation")
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.setValidator(operationFormValidator);
		binder.registerCustomEditor(OperationType.class, "operationType", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				OperationType type = operationTypeManager.getOperationType(Long.parseLong(text));
				setValue(type);
			}
		});
	}

	@RequestMapping(value = "/operations.html")
	public ModelAndView operations() throws ServletException, IOException {
		logger.info("View operations");
		return new ModelAndView("operations", "operations", operationManager.getOperations());
	}

	@RequestMapping(value = "/newOperation.html", method = RequestMethod.GET)
	public String newOperation(Model model) throws ServletException, IOException {
		logger.info("GET new operation");
		model.addAttribute("operation", new Operation());
		model.addAttribute("operationTypes", operationTypeManager.getOperationTypes());
		return "newOperation";
	}

	@RequestMapping(value = "/editOperation.html", method = RequestMethod.GET)
	public ModelAndView editOperation(@RequestParam("id") Long id, Model model) throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView mAndV = new ModelAndView("newOperation");
			mAndV.addObject("operation", operationManager.getOperation(id));
			mAndV.addObject("operationTypes", operationTypeManager.getOperationTypes());
			return mAndV;
		} else {
			return new ModelAndView("redirect:newOperation.html");
		}
	}

	@RequestMapping(value = "/deleteOperation.html")
	public ModelAndView deleteOperation(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			operationManager.removeOperation(id);
		}
		return new ModelAndView("redirect:operations.html");
	}

	@RequestMapping(value = "/newOperation.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateOperation(@ModelAttribute("operation") Operation operation, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		logger.info("POST new operation");
		logger.info(operation);
		if (result.hasErrors()) {
			return new ModelAndView("newOperation", "operation", operation);
		} else {
			if (operation.getId() != null && operation.getId() > 0) {
				operationManager.updateOperation(operation);
			} else {
				operationManager.addOperation(operation);
			}
			return new ModelAndView("redirect:operations.html");
		}
	}
	
}
