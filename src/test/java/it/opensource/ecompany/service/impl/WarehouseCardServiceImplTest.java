package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseCardService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@SpringBootTest
class WarehouseCardServiceImplTest {

    @Autowired
    private WarehouseCardService warehouseCardService;

    @Autowired
    private ProductsService productsService;

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsTest() {

        Long expected = 100L;
        Long actual = warehouseCardService.getNumberWarehouseCards();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25", "2, 10", "3, 30", "4, 35", "5,  0"})
    void getNumberWarehouseCardsByWarehouseTest(long warehouseId, long expected) {

        Long actual = warehouseCardService.getNumberWarehouseCardsBydWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25,  0"})
    void getNumberWarehouseCardsByDocumentWarehouseIdAndDocumentLineItemProductCodeTest(Long warehouseId, String productCode, Long expected) {


        Long actual = warehouseCardService.getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(warehouseId, productCode);

        assertEquals(expected, actual);

    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25", "2, 10", "3, 30", "4, 35", "5,  0",})
    void getWarehouseCardByWarehouseByPageTest(Long warehouseId, Long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseByPage(warehouseId, pageable);

        long actual = warehouseCards.getTotalElements();

        assertThat(actual, equalTo(expected));
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({
        "1, 8883780450, 1",
        "2, 8883780450, 0",
        "3, 8883780450, 0",
        "4, 8883780450, 1",
        "5, 8883780450, 0"})
    void getWarehouseCardsByWarehouseIdAndProductCodePageTest(long warehouseId, String productCode, long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductIsbn(warehouseId, productCode, pageable);

        long actual = warehouseCards.getTotalElements();

        assertEquals(expected, actual);
    }

    @EnabledIf(expression = "#{environment.acceptsProfiles('h2')}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 8883780450,  1"})
    void getWarehouseCardsByWarehouseIdAndProductCodeTest(long warehouseId, String productCode, long expected) {

        List<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseIdAndProductCode(warehouseId, productCode);

        long actual = warehouseCards.size();
        assertEquals(expected, actual);

    }

    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'h2'}", loadContext = true)
    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 8883780450,  1"})
    void getWarehouseCardsByWarehouseIdAndProductCodeByPage(long warehouseId, String productCode, long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductCodeByPage(warehouseId, productCode, pageable);

        long actual = warehouseCards.getTotalElements();
        assertEquals(expected, actual);

    }

}
