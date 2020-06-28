package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Profile("rest")
@Slf4j
@RestController
@RequestMapping("/cart")
public class CartResource {

    @Autowired
    private CategoriesService categoriesServices;

    @Autowired
    private CartBean cartBean;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private UserContext userContext;

    @GetMapping("/add/{productid}")
    public String addProductToCart(@PathVariable("productid") Long productid, @RequestHeader("referer") String referer) {

        Product product = productsService.getProductById(productid);

        cartBean.addProductToCart(product);

        log.debug("aggiunto prodotto al carrello");
        log.debug("sono presenti " + cartBean.getNumberProducts() + " prodotti nel carrello");

        return "redirect:" + referer;
    }

    @GetMapping("/deleteproduct/{productid}")
    public String deleteProductFromToCart(@PathVariable("productid") Long productid, @RequestHeader("referer") String referer) {

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

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesServices.getAll());
        uiModel.addAttribute("cartBean", cartBean);

        return "welcome";
    }
}

