package it.opensource.ecompany.web.controller;

import java.util.List;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Gestisce le richieste relative ai prodotti
 *
 * @author Paolo Bertin
 */
@Profile("html")
@Slf4j
@RequestMapping("/products")
@Controller
public class ProductsController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CartBean cartBean;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserContext userContext;

    @GetMapping("/all/all")
    public String viewAllProducts(Model uiModel) {

        List<Product> products = productsService.getAll();

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("products", products);

        log.debug("visualizza tutti i prodotti");

        return "catalog/list";
    }

    @GetMapping
    public String viewAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                        Model uiModel) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productsService.getAllByPage(pageable);

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("products", products);

        log.debug("visualizza tutti i prodotti per pagine");

        return "catalog/list";
    }

    /**
     * Visualizzare i prodotti appartenenti ad una determinata categoria per pagine
     *
     * @param categoryId categoria prodotti da visualizzare
     * @param uiModel    dati da visualizzare
     * @return nome vista
     */
    @GetMapping(value = "/{categoryId}")
    public String viewProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                               Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);
        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("products", products);

        log.debug("numero prodotti da visualizzare =" + products.getContent().size());

        return "catalog/list";
    }

    /**
     * Gestisce la richiesta di visualizzare un prodotto di una determinata
     * categoria
     *
     * @param categoryid
     * @param productid
     * @param uiModel
     * 
     * @return nome vista
     */
    @GetMapping(value = "/{categoryid}/{productid}")
    public String viewProduct(@PathVariable("categoryid") Long categoryid,
                              @PathVariable("productid") Long productid,
                              Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        Product product = productsService.getProductById(productid);
        uiModel.addAttribute("product", product);

        return "catalog/show";
    }

    /**
     * Recupera da db l'immagine di un prodotto
     *
     * @param id identificativo prodotto
     * 
     * @return immagine prodotto
     */
    @GetMapping(value = "/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {

        Product product = productsService.getProductById(id);

        return product.getImage();
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@ModelAttribute SearchForm searchForm,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size,
                                Model uiModel) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il titolo da cercare deve contenere: " + searchText);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByNameContainingByPage(searchText, pageable);

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("products", products);

        log.debug("numero prodotti da visualizzare = " + products.getContent().size());

        return "catalog/list";
    }
}
