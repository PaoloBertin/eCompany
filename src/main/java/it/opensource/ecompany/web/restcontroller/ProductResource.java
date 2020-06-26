package it.opensource.ecompany.web.restcontroller;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.web.controller.util.UrlUtil;
import it.opensource.ecompany.web.form.SearchForm;
import it.opensource.ecompany.web.restcontroller.util.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Gestisce le richieste relative ai prodotti
 *
 * @author Paolo Bertin
 */
@Profile("rest")
@Slf4j
@RequestMapping("/products")
@RestController
public class ProductResource {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<Page<Product>> viewAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getAllByPage(pageable);

        ResponseEntity<Page<Product>> responseEntity = new ResponseEntity<Page<Product>>(products, HttpStatus.OK);

        return responseEntity;
    }

    /**
     * Visualizzare i prodotti appartenenti ad una determinata categoria per pagine
     *
     * @param categoryId categoria prodotti da visualizzare
     * @return nome vista
     */
    @GetMapping(value = "/{categoryId}")
    public Page<Product> viewProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);

        return products;
    }

    /**
     * Gestisce la richiesta di visualizzare un prodotto di una determinata
     * categoria
     *
     * @param categoryid
     * @param productid
     * @return nome vista
     */
    @GetMapping(value = "/{categoryid}/{productid}")
    public Product viewProduct(@PathVariable("categoryid") Long categoryid, @PathVariable("productid") Long productid) {

        Product product = productsService.getProductById(productid);

        return product;
    }

    /**
     * Recupera da db l'immagine di un prodotto
     *
     * @param id identificativo prodotto
     * @return immagine prodotto
     */
    @GetMapping(value = "/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {

        Product product = productsService.getProductById(id);

        return product.getImage();
    }

    /**
     * Rende persistente i campi del form ricevuto
     *
     * @param product
     * @param bindingResult
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @param file
     * 
     * @return nome vista
     */
    @PostMapping
    public String create(@Valid Product product, BindingResult bindingResult, HttpServletRequest httpServletRequest,
                         RedirectAttributes redirectAttributes, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {

        log.info("Creating product");
        if (bindingResult.hasErrors()) {
            return "catalog/edit";
        }

        // redirectAttributes.addFlashAttribute("message", new Message("success",
        // messageSource.getMessage("product_save_success", new Object[]{}, locale)));

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
                product.setImage(fileContent);
            } catch (IOException ex) {
                log.error("Error saving uploaded file");
            }
            product.setImage(fileContent);
        }

        productsService.save(product);

        return "redirect:/products/"
                + UrlUtil.encodeUrlPathSegment(product.getProductid().toString(), httpServletRequest);
    }

    /**
     * Gestisce l'arrivo di un form con i campi editati
     *
     * @param product
     * @param bindingResult
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @return vista
     */
    @PostMapping(value = "/{id}")
    public String update(@Valid Product product, @PathVariable("id") Long id, BindingResult bindingResult,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {

        log.info("Updating product");

        if (bindingResult.hasErrors()) {
            messageSource.getMessage("product.save.fail", new Object[]{}, locale);
            return "catalog/edit";
        }

        // redirectAttributes.addFlashAttribute("message", new Message("success",
        // messageSource.getMessage("product.save.success", new Object[]{}, locale)));
        // rende persistenti le modifiche
        productsService.save(product);

        String url = "redirect:/products/" + product.getCategory().getCategoryid() + "/" + product.getProductid();

        return url;
    }

    /**
     * Recupera prodotto noto il suo id
     *
     * @param productid
     * @param customerForm
     * 
     * @return nome vista
     */
    @GetMapping(value = "/all/{productid}")
    public ResponseEntity<Product> updateForm(@PathVariable("productid") Long id) {

        log.debug("recupera prodotto con id=" + id);

        Product product = productsService.getProductById(id);
        ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);

        return responseEntity;
    }

    /**
     * Recupera i prodotti il cui nome contine la stringa data
     * 
     * @param searchForm
     * @param page
     * @param size
     * 
     * @return
     */
    @GetMapping("/searchProduct")
    public ResponseEntity<Page<Product>> searchProduct(SearchForm searchForm,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il titolo da cercare deve contenere: " + searchText);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productsService.getProductsByNameContainingByPage(searchText, pageable);

        log.debug("numero prodotti da visualizzare = " + products.getContent().size());

        ResponseEntity<Page<Product>> responseEntity = new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
        return responseEntity;
    }
}
