package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.SalesOrder;
import it.opensource.ecompany.service.SalesOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class SalesOrdersServiceImplTest {

    @Autowired
    private SalesOrdersService salesOrdersService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllSalesOrders() {


        List<SalesOrder> salesOrders = salesOrdersService.getAllSalesOrders();

        int expected = 10;
        int actual = salesOrders.size();

        assertThat(actual, equalTo(expected));

    }
}