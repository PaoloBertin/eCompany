package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.PurchaseOrdersService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class PurchaseOrdersServiceImplTest {

    @Autowired
    private PurchaseOrdersService purchaseOrdersService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getAllPurchaseOrdersTest() {

        int expected = 15;
        int actual = purchaseOrdersService.getAllPurchaseOrders()
                                          .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchaseOrderByIdTest() {

        double expected = 169.50;
        double actual = purchaseOrdersService.getPurchaseOrderById(1L)
                                             .getTotalAmount();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void getPurchaseOrderByCustomerIdTest() {

        int expected = 4;
        int actual = purchaseOrdersService.getPurchaseOrderByCustomer(2L)
                                          .size();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void updatePurchaseOrder() {

    }

    @Disabled
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    public void savePurchaseOrder() {

    }
}
