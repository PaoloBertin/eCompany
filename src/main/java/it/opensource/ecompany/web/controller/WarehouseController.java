package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseService;
import it.opensource.ecompany.service.WaresService;
import it.opensource.ecompany.web.form.ProductForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/admin/warehouse")
@Controller
public class WarehouseController {

    private final CategoriesService categoriesService;

    private final WarehouseService warehouseService;

    private final WaresService waresService;

    private final UserContext userContext;

    private final CartBean cartBean;

    public WarehouseController(CategoriesService categoriesService, WarehouseService warehouseService, WaresService waresService,
                               UserContext userContext, CartBean cartBean) {

        this.categoriesService = categoriesService;
        this.warehouseService = warehouseService;
        this.waresService = waresService;
        this.userContext = userContext;
        this.cartBean = cartBean;
    }

    @GetMapping("/wares")
    public String viewAllWaresInWarehouseByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "warehouseId") Long warehouseId, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Ware> wares = waresService.getAllWaresInWarehouseByPage(warehouseId, pageable);

        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories =categoriesService.getAll();
        SearchForm searchForm = new SearchForm();
        ProductForm productForm = new ProductForm();
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("searchForm",  searchForm);
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("warehouses", warehouses);
        uiModel.addAttribute("wares", wares);

        log.debug("visualizza pagina " + page + 1);

        return "warehouse/list";

    }

    @GetMapping(value = "/{warehouseId}/wares/{categoryId}")
    public String viewWaresInWarehouseByCategoryByPage(@PathVariable("warehouseId") Long warehouseId,
                                                       @PathVariable("categoryId") Long categoryId,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Ware> wares = waresService.getWaresByCategoryCategoryid(warehouseId, categoryId, pageable);
        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("productForm", new ProductForm());
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("wares", wares);

        log.debug("numero prodotti da visualizzare = " + wares.getContent()
                                                              .size());

        return "warehouse/listByPage";
    }

    @GetMapping("/{warehouseId}/wares/searchProduct")
    public String searchProduct(@PathVariable("warehouseId") Long warehouseId, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size, @ModelAttribute SearchForm searchForm,
                                Model uiModel) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il prodotto da cercare deve contenere nel titolo: " + searchText);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Ware> wares = waresService.getByWarehouseWarehouseidAndProductNameContaining(warehouseId, searchText, pageable);

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("productForm", new ProductForm());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("wares", wares);

        log.debug("numero prodotti da visualizzare = " + wares.getContent()
                                                              .size());

        return "warehouse/list";
    }
}
