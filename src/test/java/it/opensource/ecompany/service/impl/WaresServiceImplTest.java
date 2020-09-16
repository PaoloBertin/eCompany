package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.service.ProductsService;
import it.opensource.ecompany.service.WaresService;
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

@SpringBootTest
class WaresServiceImplTest {

    @Autowired
    private WaresService waresService;

    @Autowired
    private ProductsService productsService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWaresInWarehouse() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        Long warehouseId = 2L;
        Long expected = 30L;
        Page<Ware> page = waresService.getAllWaresInWarehouseByPage(warehouseId, pageable);
        Long actual = page.getTotalElements();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWaresByPage() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWaresInWarehouseByPage() {

        Long warehouseId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<Ware> page = waresService.getAllWaresInWarehouseByPage(warehouseId, pageable);

        int expected = 10;
        int actual = page.getContent().size();

        assertThat(actual, is(expected));

    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWaresByCategoryCategoryid() {

        Long warehouseId = 1L;
        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        //Page<Ware> page = waresService.getWaresByCategoryCategoryid(warehouseId, categoryId, pageable);
        Page<Ware> page = null;

        int expected = 10;
        int actual = page.getContent().size();

        assertThat(actual, is(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWarehouseWarehouseid() {

        Long warehouseId = 2L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<Ware> wares = waresService.getAllWaresInWarehouseByPage(warehouseId, pageable);

        int expected = 10;
        int actual = wares.getContent().size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getBySkuAndWarehouse() {

        Long warehousesId = 1L;
        String sku = "8883780450";
        List<Ware> wares = waresService.getBySkuAndWarehouse(sku, warehousesId);

        int expected = 1;
        int actual = wares.size();

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

        // Page<Ware> page = waresService.getByWarehouseWarehouseidAndProductNameContaining(warehouseId, stringSearch, pageable);
        Page<Ware> page = null;

        int expected = 1;
        int actual = page.getContent().size();

        assertThat(actual, is(expected));
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getAllWithProductTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));
        // Page<Ware> wares = waresService.getAllWithProduct(pageable);
        Page<Ware> wares = null;

        long expected = 84;
        long actual = wares.getTotalElements();
        assertThat(actual, is(expected));

    }
}