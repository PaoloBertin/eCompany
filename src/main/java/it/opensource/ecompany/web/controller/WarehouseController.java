package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseService;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.controller.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RequestMapping("/admin/warehouses")
@Controller
public class WarehouseController {

    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService warehouseService;

    private final MessageSource messageSource;

    public WarehouseController(WarehouseService warehouseService, UserContext userContext, CartBean cartBean, MessageSource messageSource) {

        this.warehouseService = warehouseService;
        this.messageSource = messageSource;
    }

    @GetMapping("/all")
    public String getAllWarehousesByPage(@PageableDefault Pageable pageable, Model uiModel) {

        log.debug("view page " + pageable.getPageNumber() + " warehouse list");
        Page<Warehouse> warehouses = warehouseService.getAllWarehousesByPage(pageable);
        uiModel.addAttribute("warehouses", warehouses);

        return "warehouses/warehousesList";
    }

    @GetMapping("/{warehouseId}")
    public String getWarehouseById(@PathVariable Long warehouseId, Model uiModel) {

        log.debug("view warehouse with id=" + warehouseId);

        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(warehouseId);
        uiModel.addAttribute("warehouse", warehouse.get());  // TODO invio messaggio se warehouse = null

        return "warehouses/warehouseShow";
    }

    @GetMapping()
    public String getWarehouseByName(@RequestParam String name, Model uiModel) {

        Optional<Warehouse> warehouse = warehouseService.getWarehouseByName(name);
        uiModel.addAttribute("warehouse", warehouse.get()); // TODO invio messaggio se warehouse = null

        return "warehouses/warehouseShow";
    }

    @GetMapping(value = "/{warehouseId}", params = "form")
    public String warehouseUpdateForm(@PathVariable("warehouseId") Long id, @RequestParam String form, Model uiModel) {

        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        uiModel.addAttribute("warehouse", warehouse.get()); // TODO invio messaggio se warehouse = null

        log.debug("update warehouse: " + warehouse.get()
                                                  .toString()); // TODO inviare oggetto warehouse

        return "warehouses/warehouseEdit";
    }

    @PutMapping(params = "form")
    public String warehouseUpdate(@Valid Warehouse warehouse, BindingResult bindingResult, Model uiModel,
                                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {

        Message message = null;
        if (bindingResult.hasErrors()) {
            message = new Message("error", messageSource.getMessage("warehouse.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("warehouse", warehouse);
            log.info("update failed");
            return "warehouses/warehouseEdit";
        }

        if (warehouse.getId() == null) {
            message = new Message("error", messageSource.getMessage("warehouse.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("warehouse", warehouse);
            log.info("update failed: id is null");
        }

        uiModel.asMap()
               .clear();
        warehouseService.saveWarehouse(warehouse);
        message = new Message("success", messageSource.getMessage("warehouse.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        log.info("updated warehouse with id=" + warehouse.getId());

        return "redirect:/admin/warehouses/" + UrlUtil.encodeUrlPathSegment(warehouse.getId()
                                                                                     .toString(), httpServletRequest);
    }

    @GetMapping(params = "form")
    public String createWarehouseForm(Model uiModel) {

        Warehouse warehouse = new Warehouse();
        uiModel.addAttribute("warehouse", warehouse);

        return "warehouses/warehouseNew";
    }

    @PostMapping(params = "form")
    public String createWarehouse(@Valid Warehouse warehouse, BindingResult bindingResult, Model uiModel,
                                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {

        log.info("Creating warehouse");
        Message message = null;
        if (bindingResult.hasErrors()) {
            message = new Message("error", messageSource.getMessage("warehouse.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("warehouse", warehouse);
            return "warehouses/warehouseNew";
        }

        if (warehouse.getId() != null) {
            message = new Message("error", messageSource.getMessage("warehouse.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("warehouse", warehouse);
            log.info("update failed: id not null");
        }

        uiModel.asMap()
               .clear();
        message = new Message("success", messageSource.getMessage("warehouse.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        log.info("warehouse id: " + warehouse.getId());
        warehouseService.saveWarehouse(warehouse);

        return "redirect:/admin/warehouses";
    }

    @DeleteMapping("/{warehouseId}")
    public String deleteWarehouse(@PathVariable(name = "warehouseId") Long id) {

        warehouseService.deleteWarehouse(id);

        return "warehouses/warehousesList";
    }

}
