package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.ProductForm;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RequestMapping("admin/products")
@Controller
public class ProductsAdminController {

    private static final Logger log = LoggerFactory.getLogger(ProductsAdminController.class);

    private final CategoriesService categoriesService;

    private final ProductsService productsService;

    private final CartBean cartBean;

    private final MessageSource messageSource;

    private final UserContext userContext;

    public ProductsAdminController(CategoriesService categoriesService, ProductsService productsService, CartBean cartBean,
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

    @GetMapping("/all/all")
    public String viewAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productsService.getAllByPage(pageable);

        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories = categoriesService.getAll();
        SearchForm searchForm = new SearchForm();
        ProductForm productForm = new ProductForm();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", searchForm);
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("products", products);

        log.debug("visualizza tutti i prodotti per pagine");

        return "catalog/productsListAdmin";
    }

    /**
     * Visualizzare i prodotti appartenenti ad una determinata categoria per pagine
     *
     * @param categoryId categoria prodotti da visualizzare
     * @param uiModel    dati da visualizzare
     * @return nome vista
     */
    @GetMapping("/{categoryId}/all")
    public String viewProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);
        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories = categoriesService.getAll();
        SearchForm searchForm = new SearchForm();

        ProductForm productForm = new ProductForm();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("searchForm", searchForm);
        uiModel.addAttribute("cartBean", cartBean);

        log.debug("numero prodotti da visualizzare =" + products.getContent()
                                                                .size());

        return "catalog/productsListAdmin";
    }

    /**
     * Gestisce la richiesta di visualizzare un prodotto di una determinata categoria
     *
     * @param productId
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(value = "/{productId}")
    public String viewProduct(@PathVariable("productId") Long productId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Product product = productsService.getProductById(productId);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("product", product);

        return "catalog/productShow";
    }

    /**
     * Recupera da db l'immagine di un prodotto
     *
     * @param id identificativo prodotto
     * @return immagine prodotto
     */
    @GetMapping(value = "photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {

        Product product = productsService.getProductById(id);

        return product.getImage();
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@ModelAttribute SearchForm searchForm, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il titolo da cercare deve contenere: " + searchText);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByNameContainingByPage(searchText, pageable);

        Customer customer = userContext.getCurrentCustomer();
        ProductForm productForm = new ProductForm();

        uiModel.addAttribute("searchForm", searchForm);
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("products", products);

        log.debug("numero prodotti da visualizzare = " + products.getContent()
                                                                 .size());

        return "catalog/productsListAdmin";
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
    @PostMapping
    public String createProduct(@Valid ProductForm productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam(name = "form") String form, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size,
                                @RequestParam(name = "categoryId", defaultValue = "1") Long categoryId,
                                HttpServletRequest httpServletRequest, Locale locale, Model uiModel,
                                @RequestParam(value = "image", required = false) Part image) {

        log.info("Creating product");

        String message = null;
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
            Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);
            List<Category> categories = categoriesService.getAll();
            message = messageSource.getMessage("product.save.fail", new Object[]{}, locale);

            uiModel.addAttribute("page", page);
            uiModel.addAttribute("size", size);
            uiModel.addAttribute("message", new Message("success", message));
            uiModel.addAttribute("categories", categories);
            uiModel.addAttribute("categoryId", categoryId);
            uiModel.addAttribute("productForm", productForm);
            uiModel.addAttribute("products", products);

            return "catalog/productsListAdmin";
        }

        uiModel.asMap()
               .clear();

        Product product = null;
        if (productForm.getId() == null) {
            product = new Product();
        } else {
            product = productsService.getProductById(productForm.getId());
        }
        setFieldProduct(product, productForm, image);

        productsService.saveProduct(product);

        message = messageSource.getMessage("product.save.success", new Object[]{}, locale);
        redirectAttributes.addFlashAttribute("message", new Message("success", message));

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("productForm", new ProductForm());
        uiModel.addAttribute("customer", userContext.getCurrentCustomer());

        return "redirect:/admin/products/" + categoryId + "/all";
    }

    /**
     * Recupera campi prodotto da editare ed invia al form per l'editing
     *
     * @param productId
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(path = "/all/{productId}")
    public String updateProductForm(@PathVariable("productId") Long productId, @RequestParam(name = "form") String form,
                                    @RequestParam(value = "categoryId") Long categoryId, Model uiModel) {

        log.debug("id prodotto da editare=" + productId);

        Product product = productsService.getProductById(productId);
        ProductForm productForm = new ProductForm();
        setFieldProductForm(product, productForm);

        List<Category> categories = categoriesService.getAll();

        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("categories", categories);

        return "catalog/productEdit";
    }


    private Product setFieldProduct(Product product, ProductForm productForm, Part image) {

        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setIsbn(productForm.getIsbn());
        product.setPrice(productForm.getPrice());

        String categoryName = productForm.getCategoryProduct();
        if (categoryName != null) {
            Category category = categoriesService.getCategoryByName(productForm.getCategoryProduct());
            product.setCategory(category);
        }
        setImage(product, image);

        return product;
    }

    private ProductForm setFieldProductForm(Product product, ProductForm productForm) {

        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setDescription(product.getDescription());
        productForm.setIsbn(product.getIsbn());
        productForm.setPrice(product.getPrice());

        //        productForm.setImage(product.getImage());
        productForm.setCategoryProduct(product.getCategory()
                                              .getName());
        productForm.setCategory(product.getCategory());

        return productForm;
    }

    private Product setImage(Product product, Part image) {

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

        return product;
    }

}
