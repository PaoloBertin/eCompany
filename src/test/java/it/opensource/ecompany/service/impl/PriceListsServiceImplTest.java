package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.PriceListsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceListsServiceImplTest {

    @Autowired
    private PriceListsService priceListsService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getPriceListByIdTest() {

        Long priceListId = 1L;

        String expected = "base";
        String actual = priceListsService.getPriceListById(priceListId).get().getName();
        assertEquals(expected, actual);
    }

}