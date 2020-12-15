package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseJournalServiceImplTest {

    @Autowired
    private WarehouseJournalService warehouseJournalService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJurnalTest() {

        int expected = 100;
        int actual = warehouseJournalService.getAllWarehouseJurnal().size();
        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByPageTest() {

    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByDocumentDateBetweenTest() {

    }

}