package it.opensource.ecompany.web.controller;

import java.util.List;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.*;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.SearchForm;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.MovementsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("html")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/movements")
//@Controller
public class MovementsController {

    private final CartBean cartBean;

    private final CategoriesService categoriesService;

    private final MovementsService movementsService;

    private final UserContext userContext;

    @GetMapping("/all")
    public String getAllMovements(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("movements", movementsService.getAllMovements());

        log.debug("nr. ordini=" + movementsService.getAllMovements().size());

        return "movements/list";
    }

    @GetMapping("/{movementId}")
    public String getMovementById(@PathVariable("movementId") Long id, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("movement", movementsService.getMovementById(id));

        Movement movement = movementsService.getMovementById(id);

        log.debug("visualizza ordine=" + movement.getMovementid());
        log.debug("numero lineItem=" + movement.getLineitems().size());

        return "movements/show";
    }

    @GetMapping("/all/customers/{customerId}")
    public String getMovementsByCustomer(@PathVariable("customerId") Long customerId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());

        List<Movement> movements = movementsService.getMovementByCustomer(customerId);
        uiModel.addAttribute("movements", movements);

        return "movements/list";
    }

    @GetMapping("/all/customers/checkout")
    public String viewMovements(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<Category> categories = categoriesService.getAll();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categories);

        return "movements/checkout";
    }

    @GetMapping("/save")
    public String saveMovement(Model uiModel) {

        movementsService.saveMovement();

        Customer customer = userContext.getCurrentCustomer();
        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cartBean", cartBean);
        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());

        return "welcome";
    }

}
