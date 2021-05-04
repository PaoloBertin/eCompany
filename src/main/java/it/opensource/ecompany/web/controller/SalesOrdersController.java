package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.SalesOrder;
import it.opensource.ecompany.service.SalesOrdersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Profile("html")
@Controller
public class SalesOrdersController {

    private final SalesOrdersService salesOrdersService;

    private final UserContext userContext;

    Logger log = LoggerFactory.getLogger(SalesOrdersController.class);

    public SalesOrdersController(SalesOrdersService salesOrdersService, UserContext userContext) {

        this.salesOrdersService = salesOrdersService;
        this.userContext = userContext;
    }

    @GetMapping("/admin/salesorders/all")
    public String getAllSalesOrders(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<SalesOrder> salesOrders = salesOrdersService.getAllSalesOrders();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("salesOrders", salesOrders);

        return "salesorders/salesOrdersList";
    }

    @GetMapping("/admin/salesorders/{saleorderId}")
    public String getPurchaseOrderById(@PathVariable("saleorderId") Long id, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        SalesOrder saleOrder = salesOrdersService.getSalesOrderById(id);
        SearchForm searchForm = new SearchForm();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", searchForm);
        uiModel.addAttribute("saleOrder", saleOrder);

        log.debug("visualizza ordine con id=" + saleOrder.getId());

        return "salesorders/salesOrder_show";
    }

}