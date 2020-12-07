package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Account;
import it.opensource.ecompany.repository.AccountsRepository;
import it.opensource.ecompany.service.AccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service("accountsService")
public class AccountsServiceImpl implements AccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {

        this.accountsRepository = accountsRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Account getAccountById(Long accountId) {

        return accountsRepository.findById(accountId)
                                 .orElse(new Account());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> getAllAccounts() {

        return accountsRepository.findAll();
    }

    @Override
    public boolean deposit(Long accountId, BigDecimal amount) {

        Account account = accountsRepository.findById(accountId)
                                            .get();
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(amount));

        accountsRepository.save(account);

        log.debug("depositata con successo la somma pari a " + amount);

        return true;
    }

    @Override
    public boolean withdrawal(Long accountId, BigDecimal amount) {

        Account account = accountsRepository.findById(accountId)
                                            .get();
        BigDecimal balance = account.getBalance();

        if (balance.compareTo(amount) > 0) {
            balance = balance.subtract(amount);
            account.setBalance(balance);
            accountsRepository.save(account);

            log.debug("prelevata con successo la somma pari a " + amount);

            return true;
        }

        log.debug("non e' stato possibile prelevare la somma pari a " + amount);

        return false;
    }

}
