package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.WarehouseJournalService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseJournalServiceImplTest {

    @Autowired
    private WarehouseJournalService warehouseJournalService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJurnalTest() {

        long expected = 100;
        long actual = warehouseJournalService.getNumberWarehouseJurnal();
        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByPageTest() {

    }

    @Disabled
    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByDocumentDateBetweenTest() {

    }

}