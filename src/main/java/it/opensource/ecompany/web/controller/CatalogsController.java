package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.web.controller.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Profile("html")
@Slf4j
@Controller
public class CatalogsController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CartBean cartBean;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/catalog")
    public String getAllCategoriesAdmin(Model uiModel) {

        List<Category> categories = categoriesService.getAll();
        uiModel.addAttribute("categories", categories);

        log.debug("visualizza tutte le categorie");

        return "catalog/categoriesListAdmin";
    }

    /**
     * Gestisce la richiesta di creare una nuova categoria
     *
     * @return nome vista
     */
    @GetMapping(path = "/admin/catalog", params = "form")
    public String createCategoryForm(Model uiModel) {

        Category category = new Category();

        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("category", category);

        return "/catalog/editCategory";
    }

    /**
     * Rende persistente i campi del form ricevuto
     *
     * @param category
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @param redirectAttributes
     * @param locale
     * @return nome vista
     */
    @PostMapping(path = "/admin/catalog", params = "form")
    public String create(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                         Model uiModel, HttpServletRequest httpServletRequest, Locale locale) {

        log.info("Creating category");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                                                        messageSource.getMessage("category.save.fail", new Object[]{},
                                                                                 locale)));
            uiModel.addAttribute("category", category);
            return "catalog/editCategory";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                                                                    messageSource.getMessage("category.save.success",
                                                                                             new Object[]{}, locale)));

        Category result = categoriesService.saveCategory(category);
        log.info("category id: " + result.getCategoryid());

        //        String urlRedirect = "redirect:/admin/catalog/" + UrlUtil.encodeUrlPathSegment(category.getCategoryid().toString(), httpServletRequest);
        String urlRedirect = "redirect:/admin/catalog/";

        return urlRedirect;
    }

}
