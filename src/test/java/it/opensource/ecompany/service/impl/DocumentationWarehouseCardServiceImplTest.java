package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.DocumentationWarehouseCardService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentationWarehouseCardServiceImplTest {

    @Autowired
    private DocumentationWarehouseCardService documentationWarehouseCardService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardTest() {

        Long expected = 100L;
        Long actual = documentationWarehouseCardService.getNumberDocumentations();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardBydWarehouseId() {

        Long warehouseId= 4L;

        Long expected = 35L;
        Long actual = documentationWarehouseCardService.getNumberDocumentationsBydWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardByWarehouseIdAndDocumentationWarehouseCardLineItemProductId() {

        Long warehouseId = 4L;
        Long productId = 1L;

        Long expected = 1L;
        Long actual = documentationWarehouseCardService.getNumberDocumentationsByWarehouseIdAndLineItemProductId(warehouseId, productId);

        assertEquals(expected, actual);
    }

    @Disabled
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getByWarehouseIdAndLineItemProductIdByPage() {

    }

}