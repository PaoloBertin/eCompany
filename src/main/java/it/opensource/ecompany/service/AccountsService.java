package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountsService {

    Account getAccountById(Long accountId);

    List<Account> getAllAccounts();

    boolean deposit(Long accountId, BigDecimal amount);

    boolean withdrawal(Long accountId, BigDecimal amount);
}
