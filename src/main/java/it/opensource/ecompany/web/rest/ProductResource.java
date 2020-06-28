package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Gestisce le richieste relative ai prodotti
 *
 * @author Paolo Bertin
 */
@Profile("rest")
@Slf4j
@RequestMapping("/api")
@RestController
public class ProductResource {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/all")
    public ResponseEntity<Page<Product>> getAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> pageProducts = productsService.getAllByPage(pageable);

        return ResponseEntity.ok().body(pageProducts);
    }

    /**
     * Visualizzare i prodotti appartenenti ad una determinata categoria per pagine
     *
     * @param categoryId categoria prodotti da visualizzare
     * @return nome vista
     */
    @GetMapping(value = "/products/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> pageProducts = productsService.getProductsByCategoryByPage(categoryId, pageable);

        return ResponseEntity.ok().body(pageProducts);
    }

    /**
     * Recupera un prodotto in base al suo id
     *
     * @param productid
     * 
     * @return nome vista
     */
    @GetMapping(value = "/products/all/{productid}")
    public ResponseEntity<Product> getProductById(@PathVariable("productid") Long id) {

        Product product = productsService.getProductById(id);

        return ResponseEntity.ok().body(product);
    }

    /**
     * Recupera da db l'immagine di un prodotto
     *
     * @param id identificativo prodotto
     * @return immagine prodotto
     */
    @GetMapping(value = "/api/products/photo/{productid}")
    public ResponseEntity<Product> getPhotoByProductId(@PathVariable("productid") Long id) {

        Product product = productsService.getProductById(id);

        return ResponseEntity.ok().body(product);
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
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product,
                                                 @RequestParam(value = "file", required = false) Part file) throws Exception,
                                                                                                            URISyntaxException {

        log.debug("REST request to save Product : {}", product);

        if (product.getProductid() != null) {
            throw new Exception();
        }

        // Process immagine allegata al prodotto
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

        return ResponseEntity.ok().body(product);
    }

    /**
     * Aggiorna i campi di una entita'
     *
     * @param product
     * 
     * @return vista
     */
    @PutMapping(value = "/products/{productId}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product,
                                                 @PathVariable("productId") Long id) throws Exception {

        if (product.getProductid() == null) {
            throw new Exception();
        }

        log.info("Updating product");

        // rende persistenti le modifiche
        product = productsService.save(product);

        return ResponseEntity.ok().body(product);
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
    @GetMapping("/products/searchProduct")
    public ResponseEntity<Page<Product>> searchProduct(@Valid @RequestBody SearchForm searchForm,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        String searchText = searchForm.getTextToSearch();
        log.debug("il titolo da cercare deve contenere: " + searchText);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> pageProducts = productsService.getProductsByNameContainingByPage(searchText, pageable);

        log.debug("numero prodotti trovati = " + pageProducts.getContent().size());

        return ResponseEntity.ok().body(pageProducts);
    }
}
