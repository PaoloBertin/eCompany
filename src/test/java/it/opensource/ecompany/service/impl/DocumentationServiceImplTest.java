package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.DocumentationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentationServiceImplTest {

    @Autowired
    private DocumentationService documentationService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberDocumentationsTest() {

        Long expected = 84L;
        Long actual = documentationService.getNumberDocumentations();

        assertEquals(expected, actual);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberDocumentationsBydWarehouseId() {

        Long expected = 19L;
        Long actual = documentationService.getNumberDocumentationsBydWarehouseId(4L);

        assertEquals(expected, actual);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberDocumentationsByWarehouseIdAndLineItemProductId() {

        Long expected = 19L;
        Long actual = documentationService.getNumberDocumentationsBydWarehouseId(4L);

        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWarehouseIdAndLineItemProductIdByPage() {

    }

}