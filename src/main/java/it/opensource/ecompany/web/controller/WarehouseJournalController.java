package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/warehousejournal")
@Controller
public class WarehouseJournalController {

    private final WarehouseJournalService warehouseJournalService;

    Logger log = LoggerFactory.getLogger(WarehouseJournalController.class);

    public WarehouseJournalController(WarehouseJournalService warehouseJournalService) {

        this.warehouseJournalService = warehouseJournalService;
    }

    @GetMapping("/all")
    public String getAllWarehouseJournal(@PageableDefault Pageable pageable, Model uiModel) {

        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getAllWarehouseJournalByPage(pageable);

        uiModel.addAttribute("warehouseJournals", warehouseJournals);

        return "warehouseJournalList";
    }

    @GetMapping("/all/{warehouseJournalId}")
    public String getWarehouseById(@PathVariable("warehouseJournalId") Long warehouseJournalId, Model uiModel) {

        WarehouseJournal warehouseJournal = warehouseJournalService.getWarehouseJournalById(warehouseJournalId).get();
        uiModel.addAttribute("warehouseJournal", warehouseJournal);

        return "";
    }

    @GetMapping("/{warehouseId}")
    public String getWarehouseJournalByWarehouseId(){

//        WarehouseJournal warehouseJournal = warehouseJournalService.get

        return "";
    }
}
