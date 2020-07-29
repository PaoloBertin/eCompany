package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CustomerBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class CustomerController {

    private final CustomerForm customerForm;

    private final CustomerBean customerBean;

    private final CustomersService customersService;

    private final UserContext userContext;

    private final CategoriesService categoriesService;

    private final MessageSource messageSource;

    private Message message = null;

    public CustomerController(CustomerForm customerForm, CustomerBean customerBean, CustomersService customersService,
                              UserContext userContext, CategoriesService categoriesService, MessageSource messageSource) {

        this.customerForm = customerForm;
        this.customerBean = customerBean;
        this.customersService = customersService;
        this.userContext = userContext;
        this.categoriesService = categoriesService;
        this.messageSource = messageSource;
    }

    @GetMapping("/customers/registration")
    public String customerRegistrationForm(Model uiModel) {

        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("customerBean", customerBean);
        uiModel.addAttribute("customerForm", customerForm);

        log.debug("visualizza pagina registrazione nuovo utente");

        return "customers/registration";

    }

    @PostMapping("/customers/registration")
    public String signup(@Valid CustomerForm customerForm, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {

        if (result.hasErrors()) {
            message = new Message("danger", messageSource.getMessage("customer.save.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "/customers/registration";
        }

        String username = customerForm.getUsername();

        message = null;
        if (customersService.getCustomerByUsername(username) != null) {
            // result.rejectValue("username", "label.errors.registration.username", "Username is already in use.");
            redirectAttributes.addFlashAttribute("error", "Username is already in use.");
            message = new Message("danger", messageSource.getMessage("customer.form.username.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "redirect:/customers/registration";
        }

        Customer customer = customerForm.getCustomer();
        long id = customersService.save(customer);
        message = new Message("success", messageSource.getMessage("customer.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        log.debug("salvato customer con id=" + id);

        userContext.setCurrentCustomer(customer);

        return "redirect:/";
    }

    @GetMapping("/admin/customers")
    public String viewAllCustomers(Model uiModel) {

        List<Customer> customers = customersService.getAll();
        uiModel.addAttribute("customers", customers);

        return "customers/customersListAdmin";
    }
}
