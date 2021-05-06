package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseJournalService;
import it.opensource.ecompany.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Profile("html")
@RequestMapping("/admin/warehousejournals")
@Controller
public class WarehouseJournalController {

    Logger log = LoggerFactory.getLogger(WarehouseJournalController.class);

    private final UserContext userContext;

    private final WarehouseJournalService warehouseJournalService;

    private final WarehouseService warehouseService;

    public WarehouseJournalController(UserContext userContext, WarehouseJournalService warehouseJournalService,
                                      WarehouseService warehouseService) {

        this.userContext = userContext;
        this.warehouseJournalService = warehouseJournalService;
        this.warehouseService = warehouseService;
    }

    @GetMapping("/all/home")
    public String getWarehouseJournalsHome(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                           Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<Warehouse> warehouses = warehouseService.getAllWarehousesByPage(pageable);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouses", warehouses);

        return "warehousejournal/warehouseJournalsHome";
    }

    /**
     * Displays the list of inventory journals
     *
     * @param pageable
     * @param uiModel
     * @return
     */
    @GetMapping("/all")
    public String getAllWarehouseJournalsByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                                Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getAllWarehouseJournalByPage(pageable);

        uiModel.addAttribute("page", pageable.getPageNumber());
        uiModel.addAttribute("size", pageable.getPageSize());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseJournals", warehouseJournals);

        return "warehousejournal/warehouseJournalsList";
    }

    /**
     * View inventory journal
     *
     * @param warehouseId
     * @param uiModel
     * @return
     */
    @GetMapping("/{warehouseId}")
    public String getWarehouseJournalByWarehouseId(@PathVariable("warehouseId") Long warehouseId,
                                                   @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getWarehouseJournalsByWarehouseId(warehouseId, pageable);
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseJournals", warehouseJournals);

        return "warehousejournal/warehouseJournalsListByWarehouse";
    }
}
