package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface WaresService {

    Page<Ware> getAllWaresByPage(Pageable pageable);

    Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable);

    List<Ware> getBySkuAndWarehouse(String sku, Long warehouseId);

    Ware getWareBySku(String sku);
}
