package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseJournalServiceImplTest {

    @Autowired
    private WarehouseJournalService warehouseJournalService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWarehouseJurnal() {

        List<WarehouseJournal> warehousesJournals = warehouseJournalService.getAllWarehouseJurnal();
        int expected = 84;
        int actual = warehousesJournals.size();
        assertEquals(expected, actual);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByPage() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWarehouseJournalByDocumentDateBetween() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void testGetAllWarehouseJournalByDocumentDateBetween() {

    }

}