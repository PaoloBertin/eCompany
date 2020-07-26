package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseService;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/warehouse")
@RestController
public class WarehouseResource {

    private final CategoriesService categoriesService;

    private final WarehouseService warehouseService;

    private final UserContext userContext;

    private final CartBean cartBean;

    public WarehouseResource(CategoriesService categoriesService, WarehouseService warehouseService,
                             UserContext userContext, CartBean cartBean) {

        this.categoriesService = categoriesService;
        this.warehouseService = warehouseService;
        this.userContext = userContext;
        this.cartBean = cartBean;
    }

    @GetMapping
    public String viewAllItempByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {

        // Pageable pageable = PageRequest.of(page, size);

        // Page<Warehouse> warehouse = warehouseService.getAllItemsByPage(pageable);

        // Customer customer = userContext.getCurrentCustomer();

        log.debug("visualizza pagina prodotti n. " + page + 1);

        return "warehouse/list";

    }

    @GetMapping(value = "/{categoryId}")
    public String viewProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Warehouse> warehouse = warehouseService.getByProductCategoryCategoryid(categoryId, pageable);
        // Customer customer = userContext.getCurrentCustomer();

        log.debug("numero prodotti in magazzino da visualizzare = " + warehouse.getContent()
                                                                               .size());

        return "warehouse/listByPage";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@ModelAttribute SearchForm searchForm,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il prodotto da cercare deve contenere: " + searchText);

        Pageable pageable = PageRequest.of(page, size);
        Page<Warehouse> warehouse = warehouseService.getProductsByNameContainingByPage(searchText, pageable);

        // Customer customer = userContext.getCurrentCustomer();

        log.debug("numero prodotti da visualizzare = " + warehouse.getContent()
                                                                  .size());

        return "warehouse/list";
    }
}
