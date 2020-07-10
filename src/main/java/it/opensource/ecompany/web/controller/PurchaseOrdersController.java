package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Movement;
import it.opensource.ecompany.domain.PurchaseOrder;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.PurchaseOrdersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Profile("html")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/purchaseorders")
@Controller
public class PurchaseOrdersController {

    private final CartBean cartBean;

    private final CategoriesService categoriesService;

    private final PurchaseOrdersService purchaseOrdersService;

    private final UserContext userContext;

    @GetMapping("/all")
    public String getAllPurchaseOrders(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("purchaseorders", purchaseOrdersService.getAllPurchaseOrders());

        return "purchaseorders/list";
    }

    @GetMapping("/{purchaseorderId}")
    public String getPurchaseOrderById(@PathVariable("purchaseorderId") Long id, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        PurchaseOrder purchaseOrder = purchaseOrdersService.getPurchaseOrderById(id);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("purchaseorders", purchaseOrder);

        log.debug("visualizza ordine con id=" + purchaseOrder.getId());
        log.debug("numero lineItem=" + purchaseOrder.getLineitems().size());

        return "purchaseorders/show";
    }

    @GetMapping("/all/customers/{customerId}")
    public String getPurchaseOrdersByCustomer(@PathVariable("customerId") Long customerId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        List<PurchaseOrder> purchaseOrders = purchaseOrdersService.getPurchaseOrderByCustomer(customerId);
        uiModel.addAttribute("purchaseorders", purchaseOrders);

        return "purchaseorders/list";
    }

    @GetMapping("/all/customers/checkout")
    public String viewPurchaseOrders(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories = categoriesService.getAll();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categories);

        return "purchaseorders/checkout";
    }

    @GetMapping("/save")
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
