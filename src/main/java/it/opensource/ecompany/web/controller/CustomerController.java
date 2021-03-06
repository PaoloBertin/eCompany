package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.bean.CustomerBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.controller.util.Message;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Profile("html")
@Controller
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerBean customerBean;

    private final CustomersService customersService;

    private final UserContext userContext;

    private final CategoriesService categoriesService;

    private final MessageSource messageSource;

    private Message message = null;

    public CustomerController(CustomerBean customerBean, CustomersService customersService, UserContext userContext,
                              CategoriesService categoriesService, MessageSource messageSource) {

        this.customerBean = customerBean;
        this.customersService = customersService;
        this.userContext = userContext;
        this.categoriesService = categoriesService;
        this.messageSource = messageSource;
    }

    @GetMapping("/customers/registration")
    public String customerRegistrationForm(Model uiModel) {

        CustomerForm customerForm = new CustomerForm();

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
        if (customerForm.getCustomerid() == null && customersService.getCustomerByUsername(username) != null) {
            // result.rejectValue("username", "label.errors.registration.username",
            // "Username is already in use.");
            redirectAttributes.addFlashAttribute("error", "Username is already in use.");
            message = new Message("danger", messageSource.getMessage("customer.form.username.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "redirect:/customers/registration";
        }

        String email = customerForm.getEmail();
        message = null;
        if (customerForm.getCustomerid() == null && customersService.getCustomerByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("error", "Email is already in use.");
            message = new Message("danger", messageSource.getMessage("customer.form.email.fail", new Object[]{}, locale));
            redirectAttributes.addFlashAttribute("message", message);

            return "redirect:/customers/registration";
        }

        Customer customer = customerForm.getCustomer();
        long id = customersService.saveCustomer(customer);
        message = new Message("success", messageSource.getMessage("customer.save.success", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        log.debug("salvato customer con id=" + id);

        userContext.setCurrentCustomer(customer);

        return "redirect:/";
    }

    @GetMapping("/admin/customers")
    public String viewAllCustomers(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<Customer> customers = customersService.getAllCustomers();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("customers", customers);
        uiModel.addAttribute("customerForm", new CustomerForm());

        return "customers/customersListAdmin";
    }

    @GetMapping(value = "/admin/customers/{customerId}", params = "form")
    public String updateCustomerForm(@PathVariable("customerId") Long customerId, Model uiModel) {

        log.debug("cliente da editare id=" + customerId);
        Customer customer = customersService.getCustomerById(customerId);
        CustomerForm customerForm = new CustomerForm();
        customerForm.setCustomer(customer);

        List<Customer> customers = customersService.getAllCustomers();

        uiModel.addAttribute("customers", customers);
        uiModel.addAttribute("customerForm", customerForm);

        return "customers/customersListAdmin";
    }

}
