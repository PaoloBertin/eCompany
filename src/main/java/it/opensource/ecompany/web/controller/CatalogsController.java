package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.web.controller.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequestMapping("/admin/catalog")
@Controller
public class CatalogsController {

    private final CategoriesService categoriesService;

    private final ProductsService productsService;

    private final CartBean cartBean;

    private final MessageSource messageSource;

    public CatalogsController(CategoriesService categoriesService, ProductsService productsService, CartBean cartBean,
                              MessageSource messageSource) {

        this.categoriesService = categoriesService;
        this.productsService = productsService;
        this.cartBean = cartBean;
        this.messageSource = messageSource;
    }

    /**
     * Gestisce la richiesta di creare una nuova categoria
     *
     * @return nome vista
     */
    @GetMapping(params = "form")
    public String createCategoryForm(Model uiModel) {

        Category category = new Category();

        uiModel.addAttribute("category", category);

        return "catalog/categoriesList";
    }

    /**
     * Rende persistente i campi del form ricevuto
     *
     * @param category           categoria da creare
     * @param bindingResult
     * @param uiModel
     * @param redirectAttributes
     * @param locale
     * @return nome vista
     */
    @PostMapping(params = "form")
    public String createCategory(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Model uiModel, Locale locale) {

        log.info("Creating category");

        Message message = null;
        if (bindingResult.hasErrors()) {
            message = new Message("error", messageSource.getMessage("category.save.fail", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("category", category);
            return "catalog/categoriesList";
        }

        uiModel.asMap()
               .clear();

        Category result = categoriesService.saveCategory(category);
        message = new Message("success", messageSource.getMessage("category.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        log.info("category id: " + result.getCategoryid());

        String urlRedirect = "redirect:/admin/catalog/all";

        return urlRedirect;
    }

    @DeleteMapping
    public String deleteCategory(@RequestParam("categoryid") Long id, Model uiModel) {

        List<Product> products = productsService.getProductsByCategory(id);
        for (Product product : products) {
            productsService.deleteProduct(product);
        }
        Category category = categoriesService.getCategoryById(id);
        categoriesService.deleteCategory(category);

        List<Category> categories = categoriesService.getAll();
        category = new Category();
        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("category", category);

        return "catalog/categoriesList";
    }

    /**
     * Gestisce la richiesta di modificare una categoria
     *
     * @return nome vista
     */
    @GetMapping(path = "/{categoryId}", params = "form")
    public String updateCategoryForm(@PathVariable("categoryId") Long categoryId, Model uiModel) {

        Category category = categoriesService.getCategoryById(categoryId);

        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("category", category);

        return "catalog/categoryEdit";
    }

    /**
     * Ritorna la vista di tutte le categorie
     *
     * @param page    pagina da visualizzare
     * @param size    elementi per pagina
     * @param uiModel mdello vista
     * @return nome vista
     */
    @GetMapping("/all")
    public String getAllCategories(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        List<Category> categories = categoriesService.getAll();
        Category category = new Category();

        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("category", category);

        log.debug("visualizza tutte le categorie");

        return "catalog/categoriesList";
    }

    /*
    @GetMapping("/admin/catalog/{categoryId}/all")
    public String getAllProductsByCategoryByPage(@PathVariable("categoryId") Long categoryId,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "10") int size, Model uiModel) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Product> products = productsService.getProductsByCategoryByPage(categoryId, pageable);

        List<Category> categories = categoriesService.getAll();

        Category category = new Category();

        ProductForm productForm = new ProductForm();
        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("category", category);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("productForm", productForm);

        log.debug("visualizza tutte le categorie");

        return "catalog/categoriesList";
    }
     */
}
