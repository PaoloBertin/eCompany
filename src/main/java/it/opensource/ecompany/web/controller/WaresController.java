package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseService;
import it.opensource.ecompany.service.WaresService;
import it.opensource.ecompany.web.form.SearchForm;
import it.opensource.ecompany.web.form.WareForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequestMapping("/admin/wares")
@Controller
public class WaresController {

    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

    private final WaresService waresService;

    private final WarehouseService warehouseService;

    private final UserContext userContext;

    private final CartBean cartBean;

    public WaresController(WaresService waresService, WarehouseService warehouseService, UserContext userContext, CartBean cartBean) {

        this.waresService = waresService;
        this.warehouseService = warehouseService;
        this.userContext = userContext;
        this.cartBean = cartBean;
    }

    @GetMapping
    public String viewAllWaresByPage(@ModelAttribute("wareForm") WareForm wareForm,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size,
                                     @RequestParam(name = "warehouseId", required = false) String warehouseId,
                                     Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Ware> wares = null;

        if(wareForm.getWarehouseId() == null || wareForm.getWarehouseId().equals("all")) {
            wares = waresService.getAllWaresByPage(pageable);
            warehouseId = "all";
        }
        else {
            warehouseId = wareForm.getWarehouseId();
            Long id = Long.parseLong(wareForm.getWarehouseId());
            Collection<Long> collectionId = Arrays.asList(id);
            wares = waresService.getAllWaresInWarehouseByPage(collectionId, pageable);
        }

        Customer customer = userContext.getCurrentCustomer();
        SearchForm searchForm = new SearchForm();
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("searchForm", searchForm);
        uiModel.addAttribute("wareForm", wareForm);
        uiModel.addAttribute("warehouseId", warehouseId);
        uiModel.addAttribute("warehouses", warehouses);
        uiModel.addAttribute("wares", wares);

        log.debug("visualizza pagina " + page + 1);

        return "warehouse/list";

    }
}
