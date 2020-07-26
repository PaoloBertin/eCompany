package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Slf4j
@RequestMapping("/login")
@Controller
public class LoginController {

    private final CartBean cartBean;

    private final CategoriesService categoriesService;

    private final MessageSource messageSource;

    public LoginController(CartBean cartBean, CategoriesService categoriesService, MessageSource messageSource) {

        this.cartBean = cartBean;
        this.categoriesService = categoriesService;
        this.messageSource = messageSource;
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
                             @RequestParam(value = "logout", required = false) String logout, Model uiModel,
                             Locale locale) {

        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("categories", categoriesService.getAll());

        Message message = null;
        if (error != null) {
            message = new Message("error", messageSource.getMessage("login.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
        }

        if (logout != null) {
            message = new Message("error", messageSource.getMessage("logout.save.success", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
        }

        log.debug("visualizza pagina login");

        return "welcome";
    }
}
