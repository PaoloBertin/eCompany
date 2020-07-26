package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.controller.util.UrlUtil;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/**
 * Gestisce le richieste relative ai prodotti
 *
 * @author Paolo Bertin
 */
@Slf4j
@Controller
public class ProductsController {

    private final CategoriesService categoriesService;

    private final ProductsService productsService;

    private final CartBean cartBean;

    private final MessageSource messageSource;

    private final UserContext userContext;

    public ProductsController(CategoriesService categoriesService, ProductsService productsService, CartBean cartBean,
                              MessageSource messageSource, UserContext userContext) {

        this.categoriesService = categoriesService;
        this.productsService = productsService;
        this.cartBean = cartBean;
        this.messageSource = messageSource;
        this.userContext = userContext;
    }

    @GetMapping("/products/all/all")
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

    @GetMapping("/products")
    public String viewAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

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
    @GetMapping(value = "/products/{categoryId}")
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

        log.debug("numero prodotti da visualizzare =" + products.getContent()
                                                                .size());

        return "catalog/list";
    }

    /**
     * Gestisce la richiesta di visualizzare un prodotto di una determinata categoria
     *
     * @param categoryid
     * @param productid
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(value = "/products/{categoryid}/{productid}")
    public String viewProduct(@PathVariable("categoryid") Long categoryid, @PathVariable("productid") Long productid,
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
     * @return immagine prodotto
     */
    @GetMapping(value = "/products/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {

        Product product = productsService.getProductById(id);

        return product.getImage();
    }

    @GetMapping("/products/searchProduct")
    public String searchProduct(@ModelAttribute SearchForm searchForm,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

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

        log.debug("numero prodotti da visualizzare = " + products.getContent()
                                                                 .size());

        return "catalog/list";
    }

    /**
     * Gestisce la richiesta di creare un nuovo prodotto
     *
     * @return nome vista
     */
    @GetMapping(path = "/admin/products", params = "form")
    public String createForm(Model uiModel) {

        Product product = new Product();

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("product", product);

        return "catalog/edit";
    }

    /**
     * Rende persistente i campi del form ricevuto
     *
     * @param product
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @param file
     * @return nome vista
     */
    @PostMapping(path = "/admin/products", params = "form")
    public String create(@Valid Product product, BindingResult bindingResult, RedirectAttributes attributes,
                         RedirectAttributes redirectAttributes, Model uiModel, HttpServletRequest httpServletRequest,
                         Locale locale, @RequestParam(value = "file", required = false) Part file) {

        log.info("Creating product");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                                                        messageSource.getMessage("product_save_fail", new Object[]{},
                                                                                 locale)));
            uiModel.addAttribute("product", product);
            return "catalog/edit";
        }
        uiModel.asMap()
               .clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("product_save_success",
                                                                                             new Object[]{}, locale)));

        log.info("Product id: " + product.getProductid());

        // Process upload file
        if (file != null) {
            log.info("File name: " + file.getName());
            log.info("File size: " + file.getSize());
            log.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null)
                    log.info("File inputstream is null");
                // fileContent = IOUtils.toByteArray(inputStream);
                product.setImage(fileContent);
            } catch (IOException ex) {
                log.error("Error saving uploaded file");
            }
            product.setImage(fileContent);
        }

        productsService.saveProduct(product);

        return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getProductid()
                                                                           .toString(), httpServletRequest);
    }

    /**
     * Gestisce l'arrivo di un form con i campi editati
     *
     * @param product
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @return vista
     */
    @PostMapping(path = "/admin/products/{id}", params = "form")
    public String update(@Valid Product product, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {

        log.info("Updating product");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                                                        messageSource.getMessage("product.save.fail", new Object[]{},
                                                                                 locale)));
            uiModel.addAttribute("product", product);
            return "catalog/edit";
        }

        uiModel.asMap()
               .clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("product.save.success",
                                                                                             new Object[]{}, locale)));
        // rende persistenti le modifiche
        productsService.saveProduct(product);

        String url = "redirect:/products/" + product.getCategory()
                                                    .getCategoryid() + "/" + product.getProductid();

        return url;
    }

    /**
     * Recupera campi prodotto da editare ed invia al form per l'editing
     *
     * @param productid
     * @param customerForm
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(path = "/admin/products/{productid}", params = "form")
    public String updateForm(@PathVariable("productid") Long productid, @ModelAttribute CustomerForm customerForm,
                             Model uiModel) {

        log.debug("id prodotto da editare=" + productid);

        Product product = productsService.getProductById(productid);

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("product", product);

        return "catalog/edit";
    }
}
