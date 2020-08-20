package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.AccountsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

@SpringBootTest
class AccountsServiceImplTest {

    @Autowired
    private AccountsService accountsService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAccountByIdTest() {

        BigDecimal expected = new BigDecimal(10000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(1L)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllAccountsTest() {

        int expected = 1;
        int actual = accountsService.getAllAccounts()
                                    .size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void depositTest() {

        Long accountId = 1L;
        BigDecimal deposit = new BigDecimal(100);
        accountsService.deposit(accountId, deposit);

        BigDecimal expected = new BigDecimal(10100);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void withdrawalSuccessTest() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(1000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(9000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void withdrawalFailureTest() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(11000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(10000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }
}