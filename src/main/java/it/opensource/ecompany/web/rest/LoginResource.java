package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/login")
@RestController
public class LoginResource {

    private static final Logger log = LoggerFactory.getLogger(LoginResource.class);

    private CartBean cartBean;

    private CategoriesService categoriesService;

    public LoginResource(CartBean cartBean, CategoriesService categoriesService) {

        this.cartBean = cartBean;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/form")
    public String login(Model uiModel) {

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        return "login/login";
    }

    @GetMapping
    public String loginError(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout, Model uiModel) {

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesService.getAll());

        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }

        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }

        uiModel.addAttribute("errorMessge", errorMessge);

        log.debug("visualizza pagina login");

        return "welcome";
    }

}
