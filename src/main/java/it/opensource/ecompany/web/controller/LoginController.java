package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.web.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.opensource.ecompany.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;

@Profile("html")
@Slf4j
@RequestMapping("/login")
@Controller
public class LoginController {

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

        String message = null;
        if (error != null) {
            message = "Username or Password is incorrect !!";
        }

        if (logout != null) {
            message = "You have been successfully logged out !!";
        }

        uiModel.addAttribute("message", message);

        log.debug("visualizza pagina login");

        return "welcome";
    }
}
