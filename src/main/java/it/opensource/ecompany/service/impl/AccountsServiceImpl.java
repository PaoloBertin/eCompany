package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Account;
import it.opensource.ecompany.repository.AccountsRepository;
import it.opensource.ecompany.service.AccountsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service("accountsService")
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {

        this.accountsRepository = accountsRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Account getAccountById(Long accountId) {

        return accountsRepository.findById(accountId).orElse(new Account());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> getAllAccounts() {

        return accountsRepository.findAll();
    }

    @Override
    public void deposit(BigDecimal amount) {

    }

    @Override
    public void withdrawal(BigDecimal amount) {

    }
}
