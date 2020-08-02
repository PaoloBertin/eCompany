package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Supplier;
import it.opensource.ecompany.service.SuppliersService;
import it.opensource.ecompany.web.form.SupplierForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuppliersController {

    private final SuppliersService suppliersService;

    public SuppliersController(SuppliersService suppliersService) {

        this.suppliersService = suppliersService;
    }

    @GetMapping("/admin/suppliers")
    public String viewAllSupplier(Model uiModel) {

        List<Supplier> suppliers = suppliersService.getAllSuppliers();

        uiModel.addAttribute("suppliers", suppliers);
        uiModel.addAttribute("supplierForm", new SupplierForm());

        return "suppliers/suppliersListAdmin";
    }
}
