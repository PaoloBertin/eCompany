package it.opensource.ecompany.web.controller.admin;

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
import org.springframework.context.annotation.Profile;
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
import java.util.Locale;

@Profile("html")
@Slf4j
@RequestMapping("/admin/products")
@Controller
public class ProductControllerAdmin {

    private final CategoriesService categoriesService;

    private final ProductsService productsService;

    private final CartBean cartBean;

    private final MessageSource messageSource;

    private final UserContext userContext;

    public ProductControllerAdmin(CategoriesService categoriesService, ProductsService productsService,
                                  CartBean cartBean, MessageSource messageSource, UserContext userContext) {

        this.categoriesService = categoriesService;
        this.productsService = productsService;
        this.cartBean = cartBean;
        this.messageSource = messageSource;
        this.userContext = userContext;
    }

    /**
     * Gestisce la richiesta di creare un nuovo prodotto
     *
     * @return nome vista
     */
    @GetMapping(params = "form")
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
    @PostMapping(params = "form")
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
        uiModel.asMap().clear();
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

        return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getProductid().toString(),
                                                                    httpServletRequest);
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
    @PostMapping(value = "/{id}", params = "form")
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

        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("product.save.success",
                                                                                             new Object[]{}, locale)));
        // rende persistenti le modifiche
        productsService.saveProduct(product);

        String url = "redirect:/products/" + product.getCategory().getCategoryid() + "/" + product.getProductid();

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
    @GetMapping(value = "/{productid}", params = "form")
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
