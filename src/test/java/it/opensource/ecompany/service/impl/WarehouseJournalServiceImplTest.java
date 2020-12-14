package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dbh2")
@SpringBootTest
class WarehouseJournalServiceImplTest {

    @Autowired
    private WarehouseJournalService warehouseJournalService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJurnalTest() {

        List<WarehouseJournal> warehousesJournals = warehouseJournalService.getAllWarehouseJurnal();
        int expected = 84;
        int actual = warehousesJournals.size();
        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByPageTest() {

    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByDocumentDateBetweenTest() {

    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void testGetAllWarehouseJournalByDocumentDateBetweenTest() {

    }

}