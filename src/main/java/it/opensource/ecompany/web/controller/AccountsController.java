package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Account;
import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.AccountsService;
import it.opensource.ecompany.service.UserContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Profile("html")
@RequestMapping("/admin/accounts")
@Controller
public class AccountsController {

    private final UserContext userContext;
    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService, UserContext userContext) {

        this.accountsService = accountsService;
        this.userContext = userContext;
    }

    @GetMapping
    public String viewAccounts(Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        List<Account> accounts = accountsService.getAllAccounts();
        Account account = new Account();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("account", account);
        uiModel.addAttribute("accounts", accounts);

        return "accounts/accountsList";
    }

    @GetMapping("/{accountId}")
    public String viewAccount(@PathVariable("accountId") Long accountId, Model uiModel) {

        Customer customer = userContext.getCurrentCustomer();
        Account account = accountsService.getAccountById(accountId);

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("account", account);

        return "accounts/accountShow";
    }

}
