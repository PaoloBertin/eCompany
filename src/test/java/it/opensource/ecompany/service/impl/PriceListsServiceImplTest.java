package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.PriceListsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceListsServiceImplTest {

    @Autowired
    private PriceListsService priceListsService;

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getPriceListByIdTest() {

        Long priceListId = 1L;

        var expected = "base";
        var actual = priceListsService.getPriceListById(priceListId)
                                      .get()
                                      .getPriceListName();
        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getPriceListdByPriceListNameTest() {

        String priceListName = "base";

        long expected = 1;
        long actual = priceListsService.getPriceListdByPriceListName(priceListName).getId();

        assertEquals(expected, actual);
    }

}