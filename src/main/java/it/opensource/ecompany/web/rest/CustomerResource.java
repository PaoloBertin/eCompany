package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.bean.CustomerBean;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CategoriesService;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.web.form.CustomerForm;
import it.opensource.ecompany.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Profile("rest")
@Slf4j
@RequestMapping("/customers")
@RestController
public class CustomerResource {

    @Autowired
    CustomerForm customerForm;

    @Autowired
    CustomerBean customerBean;

    @Autowired
    CustomersService customersService;

    @Autowired
    UserContext userContext;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/registration")
    public String customerRegistrationForm(Model uiModel) {

        uiModel.addAttribute("categories", categoriesService.getAll());
        uiModel.addAttribute("searchForm", new SearchForm());
        uiModel.addAttribute("customerBean", customerBean);
        uiModel.addAttribute("customerForm", customerForm);

        log.debug("visualizza pagina registrazione nuovo utente");

        return "customers/registration";

    }

    @PostMapping(value = "/registration")
    public String signup(@Valid CustomerForm customerForm,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/customers/registration";
        }

        String username = customerForm.getUsername();

        if (customersService.getCustomerByUsername(username) != null) {
            result.rejectValue("username", "label.errors.registraion.username", "Username is already in use.");
            redirectAttributes.addFlashAttribute("error", "Username is already in use.");
            return "/customers/registration";
        }

        Customer customer = new Customer();
        customer.setFirstname(customerForm.getFirstname());
        customer.setLastname(customerForm.getLastname());
        customer.setEmail(customerForm.getEmail());
        customer.setUsername(username);
        customer.setPassword(customerForm.getPassword());

        customersService.createCustomer(customer);

        customersService.setCurrentCustomer(customer);

        redirectAttributes.addFlashAttribute("message", "Success");

        return "redirect:/";
    }

    @GetMapping("/current")
    public Customer getCurrentCustomer() {

        return userContext.getCurrentCustomer();
    }
}
