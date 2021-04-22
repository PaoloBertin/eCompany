package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.dto.ProductDto;
import it.opensource.ecompany.web.form.ProductForm;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce le richieste relative ai prodotti
 *
 * @author Paolo Bertin
 */
@Profile("html")
@RequestMapping("products")
@Controller
public class ProductsController {

    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);

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

    @GetMapping
    public String viewAllProductsByPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productsService.getAllProductsByPage(pageable);
        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("products", products);

        log.debug("visualizza tutti i prodotti per pagine");

        return "catalog/productsList";
    }

    /**
     * Visualizzare i prodotti appartenenti ad una determinata categoria per pagine
     *
     * @param categoryId categoria prodotti da visualizzare
     * @param uiModel    dati da visualizzare
     * @return nome vista
     */
    @GetMapping(value = "/{categoryId}/all")
    public String viewProducstByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                               Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> productsPage = productsService.getProductsByCategoryByPage(categoryId, pageable);

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("size", size);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("categoryId", categoryId);
        uiModel.addAttribute("products", productsPage);
        uiModel.addAttribute("productForm", new ProductForm());
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);

        log.debug("numero prodotti da visualizzare =" + productsPage.getContent()
                                                                    .size());

        return "catalog/productsList";
    }

    /**
     * Gestisce la richiesta di visualizzare un prodotto di una determinata categoria
     *
     * @param productId
     * @param uiModel
     * @return nome vista
     */
    @GetMapping(value = "/all/{productId}")
    public String viewProduct(@PathVariable("productId") Long productId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        Product product = productsService.getProductById(productId);
        uiModel.addAttribute("product", product);

        return "catalog/productShow";
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

        return product.getImageProduct()
                      .getImageByte();
    }

    @GetMapping("/searchProduct")
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

        return "catalog/productsList";
    }

    private List<ProductDto> converter(List<Product> products) {

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductCode(product.getProductCode());
            productDto.setName(product.getName());
            productDto.setSubtitle(product.getSubtitle());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory());
            productDto.setImageProduct(product.getImageProduct());
            productDto.setVersion(product.getVersion());

            // ProductPrice productPrice = productPriceService.getProductPriceByPriceListNameAndProductCode("base", product.getProductCode());
            // productDto.setPrice(productPrice.getPrice());

            productDtos.add(productDto);
        }

        return productDtos;
    }
}
