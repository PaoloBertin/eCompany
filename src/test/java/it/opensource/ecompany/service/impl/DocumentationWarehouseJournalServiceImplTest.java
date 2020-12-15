package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.DocumentationWarehouseJournalService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentationWarehouseJournalServiceImplTest {

    @Autowired
    private DocumentationWarehouseJournalService documentationWarehouseJournalService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationsTest() {

        Long expected = 100L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentations();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseJournalBydWarehouseId() {

        Long warehouseId = 4L;

        Long expected = 35L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentationsBydWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationsByWarehouseIdAndLineItemProductId() {

        Long warehouseId = 4L;
        Long productId = 1L;

        Long expected = 1L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentationsByWarehouseIdAndLineItemProductId(warehouseId, productId);

        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getByWarehouseIdAndLineItemProductIdByPage() {

    }

}