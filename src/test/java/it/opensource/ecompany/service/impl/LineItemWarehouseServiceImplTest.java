package it.opensource.ecompany.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LineItemWarehouseServiceImplTest {

    @Autowired
    private LineItemWarehouseServiceImpl lineItemWarehouseService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberLineItemWarehouseJournal() {

        long expected = 100;
        long actual = lineItemWarehouseService.getNumberLineItemWarehouse();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvFileSource(resources = "/lineItemWarehouseJournalByProduct.csv", numLinesToSkip = 1)
    void getNumberLineItemWarehouseJournalByProduct(long productId, long expected) {

        long actual = lineItemWarehouseService.getNumberLineItemWarehouseByProduct(productId);
        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getLineItemWarehouseJournalById() {

    }

}