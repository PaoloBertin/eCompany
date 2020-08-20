package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountsService {

    Account getAccountById(Long accountId);

    List<Account> getAllAccounts();

    void deposit(BigDecimal amount);

    void withdrawal(BigDecimal amount);
}
