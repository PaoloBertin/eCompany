package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest()
class WarehouseServiceImplTest {

    @Autowired
    private WarehouseService warehouseService;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getAllWarehousesByPage() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("name")));
        Page<Warehouse> page = warehouseService.getAllWarehousesByPage(pageable);

        int expected = 2;
        int actual = page.getContent()
                         .size();
        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getWarehouseById() {

        String actual = warehouseService.getWarehouseById(2L)
                                        .getName();
        String expected = "magazzino02";
        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    public void getWarehouseByName() {

        String actual = warehouseService.getWarehouseByName("magazzino01")
                                        .getName();
        String expected = "magazzino01";
        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWaresIdIn() {

        List<Long> wareId = Arrays.asList(50L);
        List<Warehouse> warehouses = warehouseService.getByWaresIdIn(wareId);

        int expected = 1;
        int actual = warehouses.size();

        assertThat(actual, equalTo(expected));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void getByWaresSkuIn(){

        Collection<String> sku = Arrays.asList("8883780450");

        List<Warehouse> warehouses = warehouseService.getByWaresSkuIn(sku);

        int expected = 2;
        int actual = warehouses.size();

        assertThat(actual, equalTo(expected));
    }
}
