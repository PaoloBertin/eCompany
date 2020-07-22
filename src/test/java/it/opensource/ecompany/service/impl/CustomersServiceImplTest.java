package it.opensource.ecompany.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CustomersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomersServiceImplTest {

    String expected = null;

    String actual = null;

    @Autowired
    private CustomersService customersService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getAllCustomersTest() {

        int expected = 5;
        int actual = customersService.getAll().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerByIdVerifyAddress() {

        expected = "Vicolo Pio X";
        actual = customersService.getCustomerById(3L).getAddress().getStreet();

        assertThat(actual).isEqualTo(expected);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerByIdVerifyContact() {

        expected = "user.ecompany@dmail.com";
        actual = customersService.getCustomerById(2L).getContact().getEmail();

        assertThat(actual).isEqualTo(expected);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerTest() {

        String username = "mario.rossi";
        String password = "user";

        String expected = "mario.rossi";
        Customer actual = customersService.getCustomerByUsernameAndPassword(username, password);

        assertThat(actual.getUsername()).isEqualTo(expected);

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void verifyCustomerTest() {

        boolean expected = true;
        boolean actual = customersService.verifyCustomer("mario.rossi", "user");

        assertThat(expected).isEqualTo(actual);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void notVerifyCustomerTest() {

        boolean expected = false;
        boolean actual = customersService.verifyCustomer("mario.rossi", "mario");

        assertThat(expected).isEqualTo(actual);
    }

}
