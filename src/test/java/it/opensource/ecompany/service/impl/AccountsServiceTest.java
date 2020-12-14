package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.AccountsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

@SpringBootTest
class AccountsServiceTest {

    @Autowired
    private AccountsService accountsService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAccountByIdH2Test() {

        BigDecimal expected = new BigDecimal(100000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(1L)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllAccountsH2Test() {

        int expected = 1;
        int actual = accountsService.getAllAccounts()
                                    .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void depositH2Test() {

        Long accountId = 1L;
        BigDecimal deposit = new BigDecimal(1000);
        accountsService.deposit(accountId, deposit);

        BigDecimal expected = new BigDecimal(101000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void withdrawalSuccessH2Test() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(1000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(99000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void withdrawalFailureH2Test() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(11000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(89000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'mysql'}", loadContext = true)
    @Sql({"/db/schema-mysql.sql", "/db/data-mysql.sql"})
    @Test
    public void getAccountByIdMySQLTest() {

        BigDecimal expected = new BigDecimal(100000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(1L)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'mysql'}", loadContext = true)
    @Sql({"/db/schema-mysql.sql", "/db/data-mysql.sql"})
    @Test
    public void getAllAccountsMySQLTest() {

        int expected = 1;
        int actual = accountsService.getAllAccounts()
                                    .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'mysql'}", loadContext = true)
    @Sql({"/db/schema-mysql.sql", "/db/data-mysql.sql"})
    @Test
    public void depositMySQLTest() {

        Long accountId = 1L;
        BigDecimal deposit = new BigDecimal(1000);
        accountsService.deposit(accountId, deposit);

        BigDecimal expected = new BigDecimal(101000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'mysql'}", loadContext = true)
    @Sql({"/db/schema-mysql.sql", "/db/data-mysql.sql"})
    @Test
    void withdrawalSuccessMySQLTest() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(1000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(99000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'mysql'}", loadContext = true)
    @Sql({"/db/schema-mysql.sql", "/db/data-mysql.sql"})
    @Test
    void withdrawalFailureMySQLTest() {

        Long accountId = 1L;
        BigDecimal withdrawl = new BigDecimal(11000);
        accountsService.withdrawal(accountId, withdrawl);

        BigDecimal expected = new BigDecimal(89000);
        BigDecimal error = new BigDecimal(0.00001);
        BigDecimal actual = accountsService.getAccountById(accountId)
                                           .getBalance();

        assertThat(actual, is(closeTo(expected, error)));

    }
}
