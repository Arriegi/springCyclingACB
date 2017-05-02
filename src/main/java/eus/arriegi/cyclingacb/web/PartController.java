package eus.arriegi.cyclingacb.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.WorkOperation;
import eus.arriegi.cyclingacb.domain.Worker;
import eus.arriegi.cyclingacb.service.FabricationOrderManager;
import eus.arriegi.cyclingacb.service.OperationManager;
import eus.arriegi.cyclingacb.service.PartManager;
import eus.arriegi.cyclingacb.service.WorkOperationManager;
import eus.arriegi.cyclingacb.service.WorkerManager;
import eus.arriegi.cyclingacb.utils.reports.ColumnData;
import eus.arriegi.cyclingacb.utils.reports.ReportUtils;
import eus.arriegi.cyclingacb.web.validator.PartFormValidator;
import eus.arriegi.cyclingacb.web.validator.WorkOperationFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

@Controller
@SessionAttributes("partId")
public class PartController extends BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private PartManager partManager;
	@Autowired
	private WorkerManager workerManager;
	@Autowired
	private FabricationOrderManager fabricationOrderManager;
	@Autowired
	private OperationManager operationManager;
	@Autowired
	private WorkOperationManager workOperationManager;
	
	@Autowired
	private PartFormValidator partFormValidator;
	@Autowired
	private WorkOperationFormValidator workOperationFormValidator;;
	
	@InitBinder(value = "part")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(partFormValidator);
		binder.registerCustomEditor(Worker.class, "worker", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Worker worker = workerManager.getWorker(Long.parseLong(text));
				setValue(worker);
			}
		});
		binder.registerCustomEditor(Date.class, "date", new PropertyEditorSupport() {
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

	@RequestMapping(value = "/parts.html")
	public ModelAndView parts() throws ServletException, IOException {
		return new ModelAndView("parts", "parts", partManager.getParts());
	}
	
	@RequestMapping(value = "/newPart.html", method = RequestMethod.GET)
	public ModelAndView newPart(Model model)	throws ServletException, IOException {
		Part part = new Part();
		part.setDate(new Date());
		part.setWorker(getLoggedWorker());
		ModelAndView mAndV = new ModelAndView("newPart","part",part);
		mAndV.addObject("workers",workerManager.getWorkers());
		return mAndV;
	}
	
	@RequestMapping(value = "/newPart.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdatePart(@ModelAttribute("part") @Validated Part part,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		logger.info(result.getAllErrors());
		if (result.hasErrors()) {
			return new ModelAndView("newPart","part",part);
		} else {
			if (part.getId() != null && part.getId() > 0) {
				//partManager.updatePart(part);
			} else {
				partManager.addPart(part);
			}
			return new ModelAndView("redirect:parts.html");
		}
	}
	
	@RequestMapping(value = "/seeOperations.html")
	public ModelAndView seeOperations(HttpSession session, @RequestParam("id") Long id) {
		if (id != null && id > 0) {
			session.setAttribute("partId", id);
			Part part = partManager.getPart(id);
			return new ModelAndView("workOperations", "part", part);
		} else {
			return new ModelAndView("redirect:seeOperations.html?id" + id);
		}
	}
	
	//================ WORK OPERATION =========================//
	
	@InitBinder(value = "workOperation")
	public void InitBinderFabricationMaterial(HttpServletRequest request, ServletRequestDataBinder binder)
			throws Exception {
		binder.setValidator(workOperationFormValidator);
		binder.registerCustomEditor(Operation.class, "operation", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Operation operation = operationManager.getOperation(Long.parseLong(text));
				setValue(operation);
			}
		});
		binder.registerCustomEditor(FabricationOrder.class, "order", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				FabricationOrder order = fabricationOrderManager.getFabricationOrder(Long.parseLong(text));
				setValue(order);
			}
		});
		binder.registerCustomEditor(Date.class, "startTime", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
		binder.registerCustomEditor(Date.class, "endTime", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
	}
	
	@RequestMapping(value = "/deleteWorkOperation.html")
	public ModelAndView deleteWorkOperation(HttpSession session, @RequestParam("id") Long id) {
		if (id != null && id > 0) {
			WorkOperation workOperation = workOperationManager.getWorkOperation(id);
			Part part = partManager.getPart((Long) session.getAttribute("partId"));
			part.removeOperation(workOperation.getStartTime());
			partManager.updatePart(part);
			workOperationManager.removeWorkOperation(id);
			return new ModelAndView("redirect:seeOperations.html?id=" + part.getId());
		}
		return new ModelAndView("redirect:fabricationOrders.html");
	}
	
	@RequestMapping(value = "/editWorkOperation.html", method = RequestMethod.GET)
	public ModelAndView editWorkOperation(@RequestParam("id") Long id, Model model)
			throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView mAndV = new ModelAndView("newWorkOperation");
			mAndV.addObject("workOperation", workOperationManager.getWorkOperation(id));
			mAndV.addObject("operations", operationManager.getOperations());
			mAndV.addObject("orders", fabricationOrderManager.getFabricationOrders());
			return mAndV;
		} else {
			return new ModelAndView("redirect:newWorkOperation.html");
		}
	}
	
	@RequestMapping(value = "/newWorkOperation.html")
	public ModelAndView newWorkOperation(@RequestParam("partId") Long partId, HttpSession session) {
		if (partId != null && partId > 0) {
			session.setAttribute("partId", partId);
			ModelAndView mAndV = new ModelAndView("newWorkOperation");
			WorkOperation wOp = new WorkOperation();
			mAndV.addObject("workOperation", wOp);
			mAndV.addObject("operations", operationManager.getOperations());
			mAndV.addObject("orders", fabricationOrderManager.getFabricationOrders());
			return mAndV;
		} else {
			return new ModelAndView("redirect:seeOperations.html?id=" + partId);
		}
	}

	@RequestMapping(value = "/newWorkOperation.html", method = RequestMethod.POST)
	public ModelAndView newWorkOperation(HttpSession session, @ModelAttribute("workOperation") WorkOperation workOperation, BindingResult result) {
		Part part = partManager.getPart((Long) session.getAttribute("partId"));
		workOperation.setDayToTime(part.getDate());
		if (result.hasErrors()) {
			return new ModelAndView("newWorkOperation", "workOperation", workOperation);
		} else {
			if (workOperation.getId() != null && workOperation.getId() > 0) {
				workOperation = workOperationManager.updateWorkOperation(workOperation);
			} else {
				workOperation = workOperationManager.addWorkOperation(workOperation);
				part.addOperation(workOperation);
				partManager.updatePart(part);
			}
			return new ModelAndView("redirect:seeOperations.html?id=" + part.getId());
		}
	}
	
	// =================== REPORTS =====================//
	@RequestMapping(value = "/partsReport.html", method = RequestMethod.GET)
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
