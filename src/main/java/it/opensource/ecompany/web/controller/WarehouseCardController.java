package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseCardService;
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

import java.util.Optional;

@Profile("html")
@RequestMapping("/admin/warehouseCards")
@Controller
public class WarehouseCardController {

    Logger log = LoggerFactory.getLogger(WarehouseCardController.class);

    private final UserContext userContext;

    private final WarehouseCardService warehouseCardService;

    private final WarehouseService warehouseService;

    public WarehouseCardController(UserContext userContext, WarehouseCardService warehouseCardService,
                                   WarehouseService warehouseService) {

        this.userContext = userContext;
        this.warehouseCardService = warehouseCardService;
        this.warehouseService = warehouseService;
    }

    @GetMapping("/all/home")
    public String getWarehouseCardsHome(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                        Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<Warehouse> warehouses = warehouseService.getAllWarehousesByPage(pageable);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouses", warehouses);

        return "warehousecards/warehouseCardsHome";
    }

    /**
     * Displays the list of inventory journals
     *
     * @param pageable
     * @param uiModel
     * @return
     */
    @GetMapping("/all")
    public String getAllWarehouseCardsByPage(@PageableDefault(page = 0, size = 10, sort = "movementDate") Pageable pageable,
                                             Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<WarehouseCard> warehouseCards = warehouseCardService.getAllWarehouseCardsByPage(pageable);

        uiModel.addAttribute("page", pageable.getPageNumber());
        uiModel.addAttribute("size", pageable.getPageSize());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseCards", warehouseCards);

        return "warehousecards/warehouseCardsList";
    }

    /**
     * View inventory journal
     *
     * @param warehouseId
     * @param uiModel
     * @return
     */
    @GetMapping("/{warehouseId}")
    public String getWarehouseCardByWarehouseId(@PathVariable("warehouseId") Long warehouseId,
                                                @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                                Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(warehouseId);
        Page<WarehouseCard> warehouseCards = warehouseCardService.getAllWarehouseCardsByWarehouseIdByPage(warehouseId, pageable);

        uiModel.addAttribute("page", pageable.getPageNumber());
        uiModel.addAttribute("size", pageable.getPageSize());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouse", warehouse.isPresent() ? warehouse.get() : null);
        uiModel.addAttribute("warehouseCards", warehouseCards);

        return "warehousecards/warehouseCardsListByWarehouse";
    }

    @GetMapping("/{warehouseId}/{productCode}")
    public String getAllWarehouseCardsByWarehouseIdAndProductCode(@PathVariable("warehouseId") Long warehouseId,
                                                                  @PathVariable("productCode") String productCode,
                                                                  @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                                                                  Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Page<WarehouseCard> warehouseCards = warehouseCardService.getAllWarehouseCardsByWarehouseIdAndProductCode(warehouseId, productCode, pageable);

        uiModel.addAttribute("page", pageable.getPageNumber());
        uiModel.addAttribute("size", pageable.getPageSize());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("warehouseCards", warehouseCards);

        return "warehousecards/warehouseCardsListByWarehouseAndProductCode";
    }
}
