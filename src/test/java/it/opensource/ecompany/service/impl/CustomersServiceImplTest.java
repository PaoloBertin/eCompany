package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CustomersService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomersServiceImplTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomersService customersService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getAllCustomersTest() {

        int expected = 5;
        int actual = customersService.getAll()
                                     .size();

        assertThat(actual).isEqualTo(expected);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerByIdVerifyAddress() {

        String expected = "Vicolo Pio X";
        String actual = customersService.getCustomerById(3L)
                                        .getAddress()
                                        .getStreet();

        assertThat(actual).isEqualTo(expected);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerByIdVerifyContact() {

        String expected = "user.ecompany@dmail.com";
        String actual = customersService.getCustomerById(2L)
                                        .getContact()
                                        .getEmail();

        assertThat(actual).isEqualTo(expected);
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getCustomerTest() {

        String username = "mario.rossi";
        String password = "user";

        String expected = "mario.rossi";
        Customer actual = customersService.getCustomerByUsernameAndPassword(username, password);

        assertThat(actual).isEqualTo(expected);

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void verifyCustomerTest() {

        String encodedPassword = passwordEncoder.encode("user");

        boolean actual = customersService.verifyCustomer("mario.rossi", encodedPassword);

        assertThat(actual).isTrue();
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void notVerifyCustomerTest() {

        boolean actual = customersService.verifyCustomer("mario.rossi", "mario");

        assertThat(actual).isFalse();
    }

}
