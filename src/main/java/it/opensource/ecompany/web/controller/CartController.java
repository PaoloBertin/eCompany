package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CategoriesService categoriesServices;

    private final CartBean cartBean;

    private final ProductsService productsService;

    private final UserContext userContext;

    public CartController(CategoriesService categoriesServices, CartBean cartBean, ProductsService productsService,
                          UserContext userContext) {

        this.categoriesServices = categoriesServices;
        this.cartBean = cartBean;
        this.productsService = productsService;
        this.userContext = userContext;
    }

    @GetMapping("/add/{productid}")
    public String addProductToCart(@PathVariable("productid") Long productid,
                                   @RequestHeader("referer") String referer) {

        Product product = productsService.getProductById(productid);

        cartBean.addProductToCart(product);

        log.debug("aggiunto prodotto al carrello");
        log.debug("sono presenti " + cartBean.getNumberProducts() + " prodotti nel carrello");

        return "redirect:" + referer;
    }

    @GetMapping("/deleteproduct/{productid}")
    public String deleteProductFromToCart(@PathVariable("productid") Long productid,
                                          @RequestHeader("referer") String referer) {

        Product product = productsService.getProductById(productid);
        cartBean.deleteProductToCart(product);

        return "redirect:" + referer;
    }

    @GetMapping("/show")
    public String showCart(@ModelAttribute CustomerForm customerForm, Model uiModel) {

        log.debug("visualizza carrello");

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesServices.getAll());

        return "cart/show";
    }

    @GetMapping("/delete")
    public String deleteCart(Model uiModel) {

        cartBean.emptyCart();

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesServices.getAll());
        uiModel.addAttribute("cartBean", cartBean);

        return "welcome";
    }

}

