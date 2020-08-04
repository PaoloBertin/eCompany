package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.ProductForm;
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
import java.io.ByteArrayOutputStream;
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

    private static byte[] toByteArray(InputStream inputStream) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        return byteArrayOutputStream.toByteArray();
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
    @GetMapping(value = "/admin/products/{categoryId}/all")
    public String viewAdminProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);
        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("productForm", new ProductForm());

        log.debug("numero prodotti da visualizzare =" + products.getContent()
                                                                .size());

        return "catalog/productsListAdmin";
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
                                               @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("customer", userContext.getCurrentCustomer());
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("searchForm", new SearchForm());

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
    public String viewProduct(@PathVariable("categoryid") Long categoryid, @PathVariable("productid") Long productid, Model uiModel) {

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
    public String searchProduct(@ModelAttribute SearchForm searchForm, @RequestParam(name = "page", defaultValue = "0") int page,
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

        ProductForm productForm = new ProductForm();

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("cartBean", cartBean);

        return "catalog/edit";
    }

    /**
     * Rende persistente i campi del form ricevuto
     *
     * @param productForm
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @param image
     * @return nome vista
     */
    @PostMapping(path = "/admin/products")
    public String createProduct(@Valid ProductForm productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Model uiModel, @RequestParam(name = "form") String form,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size,
                                @RequestParam(name = "categoryId") Long categoryId, HttpServletRequest httpServletRequest, Locale locale,
                                @RequestParam(value = "image", required = false) Part image) {

        log.info("Creating product");
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
            Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);
            uiModel.addAttribute("page", page);
            uiModel.addAttribute("size", size);
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("product.save.fail", new Object[]{}, locale)));
            uiModel.addAttribute("categories", categoriesService.getAll());
            uiModel.addAttribute("categoryId", categoryId);
            uiModel.addAttribute("product", productForm);
            uiModel.addAttribute("products", products);
            return "/admin/products/" + categoryId + "/all";
        }

        uiModel.asMap()
               .clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("product.save.success", new Object[]{},
                                                                                             locale)));

        Product product = new Product();
        product = productForm.getProduct();
        log.info("Product id: " + product.getProductid());
        // Process upload file
        if (image != null) {
            log.info("File name: " + image.getName());
            log.info("File size: " + image.getSize());
            log.info("File content type: " + image.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = image.getInputStream();
                if (inputStream == null)
                    log.info("File inputstream is null");
                fileContent = toByteArray(inputStream);
                product.setImage(fileContent);
            } catch (IOException ex) {
                log.error("Error saving uploaded file");
            }
            product.setImage(fileContent);
        }

        Category category = categoriesService.getCategoryById(categoryId);
        product.setCategory(category);
        productsService.saveProduct(product);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("productForm", new ProductForm());
        uiModel.addAttribute("customer", userContext.getCurrentCustomer());

        String url = "redirect:/admin/products/" + categoryId + "/all";

        return url;
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
    public String updateProduct(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
                                RedirectAttributes redirectAttributes, Locale locale,
                                @RequestParam(value = "file", required = false) Part file) {

        log.info("Updating product");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("product.save.fail", new Object[]{}, locale)));
            uiModel.addAttribute("product", product);
            return "catalog/edit";
        }

        uiModel.asMap()
               .clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("product.save.success", new Object[]{},
                                                                                             locale)));
        // rende persistenti le modifiche
        productsService.saveProduct(product);

        String url = "redirect:/products/" + product.getCategory()
                                                    .getCategoryid() + "/" + product.getProductid();

        return url;
    }

    /**
     * Recupera campi prodotto da editare ed invia al form per l'editing
     *
     * @param productId
     * @param customerForm
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(path = "/admin/products/{productid}", params = "form")
    public String updateForm(@PathVariable("productid") Long productId, @ModelAttribute CustomerForm customerForm, Model uiModel) {

        log.debug("id prodotto da editare=" + productId);

        Product product = productsService.getProductById(productId);

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("product", product);

        return "catalog/edit";
    }


}
