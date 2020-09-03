package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WaresService {

    Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable);

    Page<Ware> getWaresByCategoryCategoryid(Long warehouseId, Long categoryId, Pageable pageable);

    Page<Ware> getByWarehouseWarehouseidAndProductNameContaining(Long warehouseid, String searchText,
                                                                         Pageable pageable);

    Ware getWareBySku(String sku);

    Integer productsWithdrawalFromWarehouse(Long warehouseId, Long productId, Integer quantity);

    Integer productsDeliveryFromWarehouse(Long warehouseId,  Long productId, Integer quantity);
}
