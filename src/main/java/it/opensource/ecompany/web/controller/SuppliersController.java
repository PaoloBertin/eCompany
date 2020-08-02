package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Supplier;
import it.opensource.ecompany.service.SuppliersService;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.SupplierForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class SuppliersController {

    private final SuppliersService suppliersService;

    private final MessageSource messageSource;

    SupplierForm supplierForm = new SupplierForm();

    private Message message = null;

    public SuppliersController(SuppliersService suppliersService, MessageSource messageSource) {

        this.suppliersService = suppliersService;
        this.messageSource = messageSource;
    }

    @GetMapping("/admin/suppliers")
    public String viewAllSupplier(Model uiModel) {

        List<Supplier> suppliers = suppliersService.getAllSuppliers();

        uiModel.addAttribute("suppliers", suppliers);
        uiModel.addAttribute("supplierForm", new SupplierForm());

        return "suppliers/suppliersListAdmin";
    }

    @GetMapping(path = "/admin/suppliers/{supplierId}", params = "form")
    public String viewSupplierForm(@PathVariable("supplierId") Long supplierId, Model uiModel) {

        Supplier supplier = suppliersService.getSupplierById(supplierId);
        supplierForm.clear();
        supplierForm.setSupplier(supplier);

        List<Supplier> suppliers = suppliersService.getAllSuppliers();

        uiModel.addAttribute("supplierForm", supplierForm);
        uiModel.addAttribute("suppliers", suppliers);

        return "suppliers/suppliersListAdmin";
    }

    @PostMapping(path = "admin/suppliers")
    public String saveSupplier(@Valid SupplierForm supplierForm, BindingResult result, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (result.hasErrors()) {
            message = new Message("danger", messageSource.getMessage("supplier.save.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "/suppliers/suppliersListAdmin";
        }

        String email = supplierForm.getEmail();
        message = null;
        if (supplierForm.getSupplierId() == null && suppliersService.getSupplierByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("error", "Email is already in use.");
            message = new Message("danger", messageSource.getMessage("supplier.form.email.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "redirect:/suppliers/suppliersListAdmin";
        }

        Supplier supplier = supplierForm.getSupplier();
        long id = suppliersService.saveSupplier(supplier).getId();
        message = new Message("success", messageSource.getMessage("supplier.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        log.debug("salvato customer con id=" + id);


        return "redirect:/suppliersListAdmin";
    }
}
