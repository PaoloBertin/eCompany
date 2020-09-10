package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface WaresService {

    long getNumberWares();

    Page<Ware> getAllWaresInWarehouseByPage(Collection<Long> warehouseId, Pageable pageable);

    List<Ware> getByWarehousesWarehouseidIn(Collection<Long> warehouseId);

    List<Ware> getBySkuAndWarehousesIn(String sku, Collection<Long> warehouseId);

    Ware getWareInWarehouse(Long Id, Long warehouseId);

    Page<Ware> getWaresByCategoryCategoryid(Long warehouseId, Long categoryId, Pageable pageable);

    Page<Ware> getByWarehousesWarehouseidAndProductName(Long warehouseId, String name, Pageable pageable);

    Page<Ware> getByWarehouseWarehouseidAndProductNameContaining(Long warehouseId, String searchText, Pageable pageable);

    Ware getWareBySku(String sku);

    Integer productsWithdrawalFromWarehouse(Long warehouseId, Long productId, Integer quantity);

    Integer productsDeliveryFromWarehouse(Long warehouseId, Long productId, Integer quantity);
}
