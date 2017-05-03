package eus.arriegi.cyclingacb.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.domain.FabricationMaterial;
import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.domain.Material;
import eus.arriegi.cyclingacb.service.ClientManager;
import eus.arriegi.cyclingacb.service.FabricationMaterialManager;
import eus.arriegi.cyclingacb.service.FabricationOrderManager;
import eus.arriegi.cyclingacb.service.MaterialManager;
import eus.arriegi.cyclingacb.web.validator.FabricationOrderFormValidator;

@Controller
public class FabricationOrderController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private FabricationOrderManager fabricationOrderManager;
	@Autowired
	private FabricationMaterialManager fabricationMaterialManager;
	@Autowired
	private MaterialManager materialManager;
	@Autowired
	private ClientManager clientManager;
	@Autowired
	private FabricationOrderFormValidator fabricationOrderFormValidator;

	@InitBinder(value = "fabricationOrder")
	protected void initBinderFabricationOrder(HttpServletRequest request, ServletRequestDataBinder binder)
			throws Exception {
		binder.setValidator(fabricationOrderFormValidator);
		binder.registerCustomEditor(Client.class, "client", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Client client = clientManager.getClient(Long.parseLong(text));
				setValue(client);
			}
		});
		binder.registerCustomEditor(Date.class, "date", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
		binder.registerCustomEditor(Date.class, "timeLimit", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
		binder.registerCustomEditor(Date.class, "closeDate", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					setValue(formatter.parseObject(text));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
	}
	
	@RequestMapping(value = "/newFabricationOrder.html", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFabricationOrder(
			@ModelAttribute("fabricationOrder") FabricationOrder fabricationOrder, BindingResult result) {
		logger.info("POST new fabricationOrder");
		logger.info(fabricationOrder);
		if (result.hasErrors()) {
			return new ModelAndView("newFabricationOrder", "fabricationOrder", fabricationOrder);
		} else {
			if (fabricationOrder.getId() != null && fabricationOrder.getId() > 0) {
				fabricationOrderManager.updateFabricationOrder(fabricationOrder);
			} else {
				fabricationOrderManager.addFabricationOrder(fabricationOrder);
			}
			return new ModelAndView("redirect:fabricationOrders.html");
		}
	}

	@RequestMapping(value = "/fabricationOrders.html")
	public ModelAndView fabricationOrders() throws ServletException, IOException {
		logger.info("View fabricationOrders");
		return new ModelAndView("fabricationOrders", "fabricationOrders",
				fabricationOrderManager.getFabricationOrders());
	}

	@RequestMapping(value = "/newFabricationOrder.html", method = RequestMethod.GET)
	public String newFabricationOrder(Model model) throws ServletException, IOException {
		logger.info("GET new fabricationOrder");
		model.addAttribute("fabricationOrder", new FabricationOrder());
		model.addAttribute("clients", clientManager.getClients());
		return "newFabricationOrder";
	}

	@RequestMapping(value = "/editFabricationOrder.html", method = RequestMethod.GET)
	public ModelAndView editFabricationOrder(@RequestParam("id") Long id, Model model)
			throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView mAndV = new ModelAndView("newFabricationOrder");
			mAndV.addObject("fabricationOrder", fabricationOrderManager.getFabricationOrder(id));
			mAndV.addObject("clients", clientManager.getClients());
			return mAndV;
		} else {
			return new ModelAndView("redirect:newFabricationOrder.html");
		}
	}

	@RequestMapping(value = "/deleteFabricationOrder.html")
	public ModelAndView deleteFabricationOrder(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			fabricationOrderManager.removeFabricationOrder(id);
		}
		return new ModelAndView("redirect:fabricationOrders.html");
	}
	
	@RequestMapping(value = "/seeMaterials.html")
	public ModelAndView seeMaterials(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			FabricationOrder order = fabricationOrderManager.getFabricationOrder(id);
			return new ModelAndView("fabricationMaterials", "order", order);
		} else {
			return new ModelAndView("redirect:seeMaterials.html?id" + id);
		}
	}
	
	// ================ FABRICATION MATERIALS =========================//
	
	@InitBinder(value = "fabricationMaterial")
	public void InitBinderFabricationMaterial(HttpServletRequest request, ServletRequestDataBinder binder)
			throws Exception {
		binder.registerCustomEditor(Material.class, "material", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Material material = materialManager.getMaterial(Long.parseLong(text));
				setValue(material);
			}
		});
	}

	@RequestMapping(value = "/newFabricationMaterial.html")
	public ModelAndView newFabricationMaterial(@RequestParam("orderId") Long orderId) {
		if (orderId != null && orderId > 0) {
			FabricationOrder order = fabricationOrderManager.getFabricationOrder(orderId);
			ModelAndView mAndV = new ModelAndView("newFabricationMaterial");
			FabricationMaterial fMat = new FabricationMaterial();
			fMat.setOrder(order);
			mAndV.addObject("fabricationMaterial", fMat);
			mAndV.addObject("materials", materialManager.getMaterials());
			return mAndV;
		} else {
			return new ModelAndView("redirect:seeMaterials.html?id=" + orderId);
		}
	}

	@RequestMapping(value = "/newFabricationMaterial.html", method = RequestMethod.POST)
	public ModelAndView newFabricationMaterial(@ModelAttribute("fabricationMaterial") FabricationMaterial fabricationMaterial,BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("newFabricationMaterial", "fabricationMaterial", fabricationMaterial);
		} else {
			if (fabricationMaterial.getId() != null && fabricationMaterial.getId() > 0) {
				fabricationMaterial = fabricationMaterialManager.updateFabricationMaterial(fabricationMaterial);
			} else {
				fabricationMaterial = fabricationMaterialManager.addFabricationMaterial(fabricationMaterial);
			}
			FabricationOrder order = fabricationMaterial.getOrder();
			order.addMaterial(fabricationMaterial);
			order = fabricationOrderManager.updateFabricationOrder(order);
			return new ModelAndView("redirect:fabricationOrders.html");
		}
	}
	
	@RequestMapping(value = "/deleteFabricationMaterial.html")
	public ModelAndView deleteFabricationMaterial(@RequestParam("id") Long id) {
		if (id != null && id > 0) {
			FabricationMaterial fMat = fabricationMaterialManager.getFabricationMaterial(id);
			FabricationOrder order = fMat.getOrder();
			fabricationMaterialManager.removeFabricationMaterial(id);
			return new ModelAndView("redirect:seeMaterials.html?id=" + order.getId());
		}
		return new ModelAndView("redirect:fabricationOrders.html");
	}
	
	@RequestMapping(value = "/editFabricationMaterial.html", method = RequestMethod.GET)
	public ModelAndView editFabricationMaterial(@RequestParam("id") Long id, Model model)
			throws ServletException, IOException {
		if (id != null && id > 0) {
			ModelAndView mAndV = new ModelAndView("newFabricationMaterial");
			mAndV.addObject("fabricationMaterial", fabricationMaterialManager.getFabricationMaterial(id));
			mAndV.addObject("materials", materialManager.getMaterials());
			return mAndV;
		} else {
			return new ModelAndView("redirect:newFabricationMaterial.html");
		}
	}

}
