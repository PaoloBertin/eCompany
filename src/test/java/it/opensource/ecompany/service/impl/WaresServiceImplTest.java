package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.service.WaresService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class WaresServiceImplTest {

    @Autowired
    private WaresService waresService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWares() {

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getNumberWaresInWarehouseId() {

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
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));

    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getWaresByCategoryCategoryid() {

        Long warehouseId = 1L;
        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<Ware> page = waresService.getWaresByCategoryCategoryid(warehouseId, categoryId, pageable);

        int expected = 10;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getProductsByNameContainingByPage() {

        Long warehouseId = 1L;
        String stringSearch = "Java";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")));

        Page<Ware> page = waresService.getByWarehouseWarehouseidAndProductNameContaining(warehouseId, stringSearch,
                                                                                         pageable);

        int expected = 6;
        int actual = page.getContent()
                         .size();

        assertThat(actual, is(expected));
    }
}