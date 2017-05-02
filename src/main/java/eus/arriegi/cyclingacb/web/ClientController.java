package eus.arriegi.cyclingacb.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.service.ClientManager;
import eus.arriegi.cyclingacb.utils.reports.ColumnData;
import eus.arriegi.cyclingacb.utils.reports.ReportUtils;
import eus.arriegi.cyclingacb.web.validator.ClientFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

@Controller
public class ClientController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ClientManager clientManager;

	@Autowired
	private ClientFormValidator clientFormValidator;

	@InitBinder("client")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(clientFormValidator);
	}

	@RequestMapping(value = "/clients.html")
	public ModelAndView clients(HttpSession session, HttpServletRequest request) throws ServletException, IOException {
		return new ModelAndView("clients", "clients", clientManager.getClients());
	}

	@RequestMapping(value = "/newClient.html", method = RequestMethod.GET)
	public ModelAndView newClient() throws ServletException, IOException {
		return new ModelAndView("newClient", "client", new Client());
	}

	@RequestMapping(value = "/editClient.html", method = RequestMethod.GET)
	public ModelAndView editClient(@RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			return new ModelAndView("newClient", "client", clientManager.getClient(id));
		} else {
			return new ModelAndView("redirect:newClient.html");
		}
	}

	@RequestMapping(value = "/deleteClient.html")
	public ModelAndView deleteClient(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			clientManager.removeClient(id);
		}
		return new ModelAndView("redirect:clients.html");
	}

	@RequestMapping(value = "/newClient.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateClient(@ModelAttribute("clientForm") @Validated Client client, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("newClient", "client", client);
		} else {
			if (client.getId() != null && client.getId() > 0) {
				clientManager.updateClient(client);
			} else {
				clientManager.addClient(client);
			}
			return new ModelAndView("redirect:clients.html");
		}
	}

	// =================== REPORTS =====================//
	@RequestMapping(value = "/clientReport.pdf", method = RequestMethod.GET)
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
		ReportUtils<Client> reportUtils = new ReportUtils<>("Clientes", null,clientManager.getClients());
		ColumnData colData = new ColumnData("Cliente","name","string");
		List<ColumnData> columns = new ArrayList<ColumnData>();
		columns.add(colData);
		colData = new ColumnData("Persona Contacto", "contactName","string");
		columns.add(colData);
		return reportUtils.getBuilder(columns);
	}
	
}
