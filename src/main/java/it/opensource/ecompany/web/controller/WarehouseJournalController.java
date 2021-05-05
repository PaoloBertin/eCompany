package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseJournalService;
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

import java.util.Optional;

@Profile("html")
@RequestMapping("/admin/warehousejournals")
@Controller
public class WarehouseJournalController {

    Logger log = LoggerFactory.getLogger(WarehouseJournalController.class);

    private final UserContext userContext;

    private final WarehouseJournalService warehouseJournalService;


    public WarehouseJournalController(UserContext userContext, WarehouseJournalService warehouseJournalService) {

        this.userContext = userContext;
        this.warehouseJournalService = warehouseJournalService;
    }

    /**
     * Displays the list of inventory journals
     *
     * @param pageable
     * @param uiModel
     * @return
     */
    @GetMapping("/all")
    public String getAllWarehouseJournalsByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getAllWarehouseJournalByPage(pageable);
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseJournals", warehouseJournals);

        return "warehousejournal/warehouseJournal_list";
    }

    /**
     * View inventory journal
     *
     * @param warehouseJournalId
     * @param uiModel
     * @return
     */
    @GetMapping("/{warehouseJournalId}")
    public String getWarehouseById(@PathVariable("warehouseJournalId") Long warehouseJournalId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Optional<WarehouseJournal> warehouseJournal = warehouseJournalService.getWarehouseJournalById(warehouseJournalId);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseJournal", warehouseJournal.isPresent() ? warehouseJournal.get() : new WarehouseJournal());

        return "";
    }
}
