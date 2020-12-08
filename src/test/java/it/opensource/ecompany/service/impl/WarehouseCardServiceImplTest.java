package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseCardService;
import it.opensource.ecompany.service.dto.WareDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsTest() {

        Long expected = 84L;
        Long actual = warehouseCardService.getNumberWarehouseCards();

        assertEquals(expected, actual);
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsByWarehouseTest() {

        Long warehouseId = 4L;
        Long expected = 19L;
        Long actual = warehouseCardService.getNumberWarehouseCardsBydWarehouseId(warehouseId);

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWarehouseCardsByWarehouseIdAndLineItemProductIdTest() {

        Long warehouseId = 4L;
        Long productId = 22L;

        Long expected = 19L;
        Long actual = warehouseCardService.getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(warehouseId, productId);

        assertThat(actual, is(expected));

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWarehouseCardByWarehouseByPageTest() {

        Long warehouseId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseByPage(warehouseId, pageable);

        long expected = 25;
        long actual = warehouseCards.getTotalElements();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWarehouseCardsByWarehouseIdAndProductCodeTest() {

        Long warehouseId = 1L;
        String productCode = "8883780450";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductIsbn(warehouseId, productCode, pageable);

        long expected = 2;
        long actual = warehouseCards.getTotalElements();

        assertThat(actual, equalTo(expected));
    }


    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWarehouseCardsByWarehouseIdAndProductIdTest() {

        Long warehouseId = 1L;
        Long productId = 1L;
        List<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseIdAndProductId(warehouseId, productId);

        long expected = 2;
        long actual = warehouseCards.size();
        assertThat(actual, is(expected));

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWarehouseCardsByWarehouseIdAndProductIdByPage() {

        Long warehouseId = 1L;
        Long productId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductIdByPage(warehouseId, productId, pageable);

        long expected = 2;
        long actual = warehouseCards.getTotalElements();
        assertThat(actual, is(expected));

    }

}
