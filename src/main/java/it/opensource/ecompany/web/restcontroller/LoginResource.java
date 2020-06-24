package it.opensource.ecompany.web.restcontroller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@Slf4j
@RequestMapping("/login")
@RestController
public class LoginResource {

    @Autowired
    private CartBean cartBean;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/form")
    public String login(Model uiModel) {

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        return "login/login";
    }

    @GetMapping
    public String loginError(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model uiModel) {

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
