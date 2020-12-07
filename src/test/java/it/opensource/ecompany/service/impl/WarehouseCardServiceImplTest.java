package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.dto.WareDto;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WarehouseCardService;
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
    void getNumberWaresInWarehouse() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Long warehouseId = 1L;
        Long expected = 25L;
        Page<WarehouseCard> page = warehouseCardService.getAllWarehouseCardsInWarehouseByPage(warehouseId, pageable);
        Long actual = page.getTotalElements();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWaresByPage() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWarehouseCardsInWarehouseByPage() {

        Long warehouseId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WarehouseCard> page = warehouseCardService.getAllWarehouseCardsInWarehouseByPage(warehouseId, pageable);

        int expected = 10;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWarehouseWarehouseid() {

        Long warehouseId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WarehouseCard> wares = warehouseCardService.getAllWarehouseCardsInWarehouseByPage(warehouseId, pageable);

        int expected = 10;
        int actual = wares.getContent()
                          .size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void findByWarehousesWarehouseidAndProductName() {

        Long warehouseId = 1L;
        String name = "";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
    }


    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getProductsByNameContainingStringByPage() {

        Long warehouseId = 1L;
        String stringSearch = "Apple";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

//        Page<WarehouseCard> page = warehouseCardService.getByWarehouseWarehouseidAndProductNameContaining(warehouseId, stringSearch, pageable);
        Page<WarehouseCard> page = null;

        int expected = 1;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWithProductTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        //Page<WarehouseCard> wares = warehouseCardService.getAllWithProduct(pageable);
        Page<WarehouseCard> wares = null;

        long expected = 84L;
        long actual = wares.getTotalElements();
//        long actual = 84L;
        assertEquals(expected, actual);

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWarehouseIdAndWarehouseCardProductIdTest() {

        Long warehouseId = 1L;
        Long warehouseCardProductId = 1L;
        List<WarehouseCard> wares = warehouseCardService.getByWarehouseIdAndWarehouseCardProductId(warehouseId, warehouseCardProductId);

        long expected = 84L;
        long actual = wares.size();
//        long actual = 84L;
        assertThat(actual, is(expected));

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWaresWithProductPageableTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Page<WareDto> wares = warehouseCardService.getAllWaresWithProductPageable(pageable);

        long expected = 84L;
        long actual = wares.getTotalElements();
//        long actual =84L;
        assertThat(actual, is(expected));

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWaresInWarehousePageable() {

        Long warehouseId = 2L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<WareDto> wares = warehouseCardService.getAllWaresInWarehousePageable(warehouseId, pageable);

        long expected = 30L;
        long actual = wares.getTotalElements();
//        long actual = 30L;
        assertThat(actual, is(expected));
    }
}
