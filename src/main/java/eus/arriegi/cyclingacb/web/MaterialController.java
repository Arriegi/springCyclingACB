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

import eus.arriegi.cyclingacb.domain.Material;
import eus.arriegi.cyclingacb.service.MaterialManager;
import eus.arriegi.cyclingacb.utils.reports.ColumnData;
import eus.arriegi.cyclingacb.utils.reports.ReportUtils;
import eus.arriegi.cyclingacb.web.validator.MaterialFormValidator;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

@Controller
public class MaterialController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MaterialManager materialManager;

	@Autowired
	private MaterialFormValidator materialFormValidator;

	@InitBinder("material")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(materialFormValidator);
	}

	@RequestMapping(value = "/materials.html")
	public ModelAndView materials() throws ServletException, IOException {
		return new ModelAndView("materials", "materials", materialManager.getMaterials());
	}

	@RequestMapping(value = "/newMaterial.html", method = RequestMethod.GET)
	public ModelAndView newMaterial() throws ServletException, IOException {
		return new ModelAndView("newMaterial", "material", new Material());
	}

	@RequestMapping(value = "/editMaterial.html", method = RequestMethod.GET)
	public ModelAndView editMaterial(@RequestParam("id") Long id) throws ServletException, IOException {
		if (id != null && id > 0) {
			return new ModelAndView("newMaterial", "material", materialManager.getMaterial(id));
		} else {
			return new ModelAndView("redirect:newMaterial.html");
		}
	}

	@RequestMapping(value = "/deleteMaterial.html")
	public ModelAndView deleteMaterial(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			materialManager.removeMaterial(id);
		}
		return new ModelAndView("redirect:materials.html");
	}

	@RequestMapping(value = "/newMaterial.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateMaterial(@ModelAttribute("materialForm") @Validated Material material,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("newMaterial", "material", material);
		} else {
			if (material.getId() != null && material.getId() > 0) {
				materialManager.updateMaterial(material);
			} else {
				materialManager.addMaterial(material);
			}
			return new ModelAndView("redirect:materials.html");
		}
	}

	// =================== REPORTS =====================//
	@RequestMapping(value = "/materialsReport.pdf", method = RequestMethod.GET)
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
		ReportUtils<Material> reportUtils = new ReportUtils<>("Materiales", null, materialManager.getMaterials());
		ColumnData colData = new ColumnData("Material", "name", "string");
		List<ColumnData> columns = new ArrayList<ColumnData>();
		columns.add(colData);
		return reportUtils.getBuilder(columns);
	}
}
