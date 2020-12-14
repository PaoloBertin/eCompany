package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.DocumentationWarehouseCardService;
import it.opensource.ecompany.service.DocumentationWarehouseJournalService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentationWarehouseJournalServiceImplTest {

    @Autowired
    private DocumentationWarehouseJournalService documentationWarehouseJournalService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationsTest() {

        Long expected = 84L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentations();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationsBydWarehouseId() {

        Long expected = 19L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentationsBydWarehouseId(4L);

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationsByWarehouseIdAndLineItemProductId() {

        Long expected = 19L;
        Long actual = documentationWarehouseJournalService.getNumberDocumentationsBydWarehouseId(4L);

        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getByWarehouseIdAndLineItemProductIdByPage() {

    }

}