package it.opensource.ecompany.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LineItemWarehouseJournalServiceImplTest {

    @Autowired
    private LineItemWarehouseJournalServiceImpl lineItemWarehouseJournalService;

    @Test
    void getNumberLineItemWarehouseJournal() {

        long expected = 100;
        long actual = lineItemWarehouseJournalService.getNumberLineItemWarehouseJournal();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvFileSource(resources = "/lineItemWarehouseJournalByProduct.csv", numLinesToSkip = 1)
    void getNumberLineItemWarehouseJournalByProduct(long productId, long expected) {

        long actual = lineItemWarehouseJournalService.getNumberLineItemWarehouseJournalByProduct(productId);
        assertEquals(expected, actual);
    }

    @Test
    void getLineItemWarehouseJournalById() {

    }

}