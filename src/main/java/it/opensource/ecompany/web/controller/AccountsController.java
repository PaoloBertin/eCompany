package it.opensource.ecompany.web.controller;

import it.opensource.ecompany.domain.Account;
import it.opensource.ecompany.service.AccountsService;
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

    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {

        this.accountsService = accountsService;
    }

    @GetMapping
    public String viewAccounts(Model uiModel) {

        List<Account> accounts = accountsService.getAllAccounts();
        Account account = new Account();
        uiModel.addAttribute("account", account);
        uiModel.addAttribute("accounts", accounts);

        return "accounts/accountsList";
    }

    @GetMapping("/{accountId}")
    public String viewAccount(@PathVariable("accountId") Long accountId, Model uiModel) {

        Account account = accountsService.getAccountById(accountId);
        uiModel.addAttribute("account", account);

        return "accounts/accountShow";
    }

}
