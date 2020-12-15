package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseCardService;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseCardServiceImplTest {

    @Autowired
    private WarehouseCardService warehouseCardService;

    @Autowired
    private ProductsService productsService;

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsTest() {

        Long expected = 100L;
        Long actual = warehouseCardService.getNumberWarehouseCards();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25", "2, 10", "3, 30", "4, 35", "5,  0"})
    void getNumberWarehouseCardsByWarehouseTest(long warehouseId, long expected) {

        Long actual = warehouseCardService.getNumberWarehouseCardsBydWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25,  0"})
    void getNumberWarehouseCardsByDocumentWarehouseIdAndDocumentLineItemProductIdTest(Long warehouseId, Long productId, Long expected) {


        Long actual = warehouseCardService.getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(warehouseId, productId);

        assertEquals(expected, actual);

    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25", "2, 25", "3, 25", "4, 25", "5, 25",})
    void getWarehouseCardByWarehouseByPageTest(Long warehouseId, Long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseByPage(warehouseId, pageable);

        long actual = warehouseCards.getTotalElements();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 8883780450, 1", "2, 8883780450, 1", "3, 8883780450, 1", "4, 8883780450, 1", "5, 8883780450, 1",})
    void getWarehouseCardsByWarehouseIdAndProductCodeTest(long warehouseId, String productCode, long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductIsbn(warehouseId, productCode, pageable);

        long actual = warehouseCards.getTotalElements();

        assertEquals(expected, actual);
    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25,  0"})
    void getWarehouseCardsByWarehouseIdAndProductIdTest(long warehouseId, long productId, long expected) {

        List<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseIdAndProductId(warehouseId, productId);

        long actual = warehouseCards.size();
        assertEquals(expected, actual);

    }

    @Sql({"/db/schema-h2.sql", "/db/data-h2.sql"})
    @ParameterizedTest
    @CsvSource({"1, 25,  0"})
    void getWarehouseCardsByWarehouseIdAndProductIdByPage(long warehouseId, long productId, long expected) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductIdByPage(warehouseId, productId, pageable);

        long actual = warehouseCards.getTotalElements();
        assertEquals(expected, actual);

    }

}
