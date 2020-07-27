package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WaresService {

    public Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable);

    public Page<Ware> getWaresByCategoryCategoryid(Long warehouseId, Long categoryId, Pageable pageable);

    public Page<Ware> getByWarehouseWarehouseidAndProductNameContaining(Long warehouseid, String searchText,
                                                                         Pageable pageable);

    public Ware getWareBySku(String sku);
}
