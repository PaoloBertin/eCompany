package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.WatchEvent;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    void findBySku() {

//        Warehouse actual = warehouseService.getWarehouseBySku("B0002");
        Warehouse expected = new Warehouse();

    }
}