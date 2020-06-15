package it.opensource.ecompany.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CatalogsController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/catalog")
    public String getAllCategories(Model uiModel) {

        List<Category> categories = categoriesService.getAll();
        uiModel.addAttribute("categories", categories);
        
        log.debug("visualizza tutte le categorie");

        return "catalog/list";
    }
}
