package eus.arriegi.cyclingacb.web;

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

import eus.arriegi.cyclingacb.domain.OperationType;
import eus.arriegi.cyclingacb.service.OperationTypeManager;
import eus.arriegi.cyclingacb.utils.reports.ColumnData;
import eus.arriegi.cyclingacb.utils.reports.ReportUtils;
import eus.arriegi.cyclingacb.web.validator.OperationTypeFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

@Controller
public class OperationTypeController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private OperationTypeManager operationTypeManager;

	@Autowired
	private OperationTypeFormValidator operationTypeFormValidator;

	@InitBinder("operationType")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(operationTypeFormValidator);
	}

	@RequestMapping(value = "/operationTypes.html")
	public ModelAndView operationTypes() throws ServletException, IOException {
		return new ModelAndView("operationTypes", "operationTypes", operationTypeManager.getOperationTypes());
	}

	@RequestMapping(value = "/newOperationType.html", method = RequestMethod.GET)
	public ModelAndView newOperationType() throws ServletException, IOException {
		return new ModelAndView("newOperationType", "operationType", new OperationType());
	}

	@RequestMapping(value = "/editOperationType.html", method = RequestMethod.GET)
	public ModelAndView editOperationType(@RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			return new ModelAndView("newOperationType", "operationType", operationTypeManager.getOperationType(id));
		} else {
			return new ModelAndView("redirect:newOperationType.html");
		}
	}

	@RequestMapping(value = "/deleteOperationType.html")
	public ModelAndView deleteOperationType(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			operationTypeManager.removeOperationType(id);
		}
		return new ModelAndView("redirect:operationTypes.html");
	}

	@RequestMapping(value = "/newOperationType.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateOperationType(
			@ModelAttribute("operationTypeForm") @Validated OperationType operationType, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("newOperationType", "operationType", operationType);
		} else {
			if (operationType.getId() != null && operationType.getId() > 0) {
				operationTypeManager.updateOperationType(operationType);
			} else {
				operationTypeManager.addOperationType(operationType);
			}
			return new ModelAndView("redirect:operationTypes.html");
		}
	}

	// =================== REPORTS =====================//
	@RequestMapping(value = "/operationTypesReport.pdf", method = RequestMethod.GET)
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
		ReportUtils<OperationType> reportUtils = new ReportUtils<>("Tipos de operación", null,
				operationTypeManager.getOperationTypes());
		ColumnData colData = new ColumnData("Tipo de Operación", "name", "string");
		List<ColumnData> columns = new ArrayList<ColumnData>();
		columns.add(colData);
		return reportUtils.getBuilder(columns);
	}
}
