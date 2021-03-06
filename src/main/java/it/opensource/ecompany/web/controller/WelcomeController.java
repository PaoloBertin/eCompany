package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Gestisce richiesta alla home page
 *
 * @author Paolo Bertin
 */
@Profile("html")
@Controller
public class WelcomeController {

    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    private final UserContext userContext;

    private final CategoriesService categoriesService;

    private final CartBean cartBean;

    public WelcomeController(UserContext userContext, CategoriesService categoriesService, CartBean cartBean) {

        this.userContext = userContext;
        this.categoriesService = categoriesService;
        this.cartBean = cartBean;
    }

    /**
     * Indirizza la richiesta alla home page
     *
     * @return modelAndView nome vista
     */
    @GetMapping(value = "/")
    public String welcome(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());

        log.debug("visualizza home page");

        return "welcome";
    }

    /**
     * Indirizza la richiesta alla home page amministrativa
     *
     * @return modelAndView nome vista
     */
    @GetMapping(value = "/admin")
    public String welcomeAdmin(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("customerForm", new CustomerForm());

        log.debug("visualizza home page amministrativa");

        return "welcomeAdmin";
    }

}
