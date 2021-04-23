package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.service.DocumentationWarehouseService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentationWarehouseServiceImplTest {

    @Autowired
    private DocumentationWarehouseService documentationWarehouseService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardTest() {

        Long expected = 100L;
        Long actual = documentationWarehouseService.getNumberDocumentations();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardBydWarehouseId() {

        Long warehouseId= 4L;

        Long expected = 35L;
        Long actual = documentationWarehouseService.getNumberDocumentationsBydWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberDocumentationWarehouseCardByWarehouseIdAndDocumentationWarehouseCardLineItemProductCode() {

        Long warehouseId = 4L;
        String productCode = "8883780450";

        Long expected = 1L;
        Long actual = documentationWarehouseService.getNumberDocumentationsByWarehouseIdAndLineItemProductCode(warehouseId, productCode);

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getByWarehouseIdAndLineItemProductIdByPage() {

    }

}