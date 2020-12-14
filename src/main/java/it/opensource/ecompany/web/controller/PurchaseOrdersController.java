package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.PurchaseOrder;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.PurchaseOrdersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PurchaseOrdersController {

    Logger log = LoggerFactory.getLogger(PurchaseOrdersController.class);

    private final CartBean cartBean;

    private final CategoriesService categoriesService;

    private final PurchaseOrdersService purchaseOrdersService;

    private final UserContext userContext;

    public PurchaseOrdersController(CategoriesService categoriesService, CartBean cartBean,
                                    PurchaseOrdersService purchaseOrdersService, UserContext userContext) {

        this.categoriesService = categoriesService;
        this.cartBean = cartBean;
        this.purchaseOrdersService = purchaseOrdersService;
        this.userContext = userContext;
    }

    @GetMapping("/admin/purchaseorders/all")
    public String getAllPurchaseOrders(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("purchaseOrders", purchaseOrdersService.getAllPurchaseOrders());

        return "purchaseorders/list";
    }

    @GetMapping("/purchaseorders/{purchaseorderId}")
    public String getPurchaseOrderById(@PathVariable("purchaseorderId") Long id, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        PurchaseOrder purchaseOrder = purchaseOrdersService.getPurchaseOrderById(id);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("purchaseOrder", purchaseOrder);

        log.debug("visualizza ordine con id=" + purchaseOrder.getId());
        log.debug("numero lineItem=" + purchaseOrder.getLineItemPurchaseOrders().size());

        return "purchaseorders/show";
    }

    @GetMapping("/purchaseorders/all/customers/{customerId}")
    public String getPurchaseOrdersByCustomer(@PathVariable("customerId") Long customerId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        List<PurchaseOrder> purchaseOrders = purchaseOrdersService.getPurchaseOrderByCustomer(customerId);
        uiModel.addAttribute("purchaseOrders", purchaseOrders);

        return "purchaseorders/list";
    }

    @GetMapping("/purchaseorders/all/customers/checkout")
    public String viewPurchaseOrders(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories = categoriesService.getAll();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categories);

        return "purchaseorders/checkout";
    }

    @GetMapping("/purchaseorders/save")
    public String savePurchaseOrdewr(Model uiModel) {

        purchaseOrdersService.savePurchaseOrder();

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());

        return "welcome";
    }
}
