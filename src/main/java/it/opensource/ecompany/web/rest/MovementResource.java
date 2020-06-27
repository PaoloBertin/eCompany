package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Movement;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.MovementsService;
import it.opensource.ecompany.service.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Profile("rest")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/movements")
@RestController
public class MovementResource {

    private final CartBean cartBean;

    private final CategoriesService categoriesService;

    private final MovementsService movementsService;

    private final UserContext userContext;

    @GetMapping("/all")
    public String getAllMovements() {

        Customer customer = userContext.getCurrentCustomer();

        List<Movement> movements = movementsService.getAllMovements();

        log.debug("nr. ordini=" + movementsService.getAllMovements().size());

        return "movements/list";
    }

    @GetMapping("/{movementId}")
    public String getMovementById(@PathVariable("movementId") Long id) {

        Customer customer = userContext.getCurrentCustomer();

        Movement movement = movementsService.getMovementById(id);

        return "movements/show";
    }

    @GetMapping("/all/customers/{customerId}")
    public String getMovementsByCustomer(@PathVariable("customerId") Long customerId) {

        Customer customer = userContext.getCurrentCustomer();

        List<Movement> movements = movementsService.getMovementByCustomer(customerId);

        return "movements/list";
    }

    @GetMapping("/all/customers/{customerId}/checkout")
    public String viewMovements(@PathVariable("customerId") Long customerId) {

        Customer customer = userContext.getCurrentCustomer();

        return "movements/checkout";
    }

    @GetMapping("/save")
    public String saveMovement() {

        movementsService.saveMovements();

        Customer customer = userContext.getCurrentCustomer();

        return "welcome";
    }

}
