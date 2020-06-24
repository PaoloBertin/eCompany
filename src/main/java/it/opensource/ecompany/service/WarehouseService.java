package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    public Page<Warehouse> getAllItemsByPage(Pageable pageable);

    public Page<Warehouse> getByProductCategoryCategoryid(Long id, Pageable pageable);

    public Page<Warehouse> getProductsByNameContainingByPage(String searchText, Pageable pageable);

    public Warehouse getWarehouseBySku(String sku);

    public Integer reducesProductQuantityInStock(Integer sold);

    public void deleteWarehose(Warehouse warehouse);
}
