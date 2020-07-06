package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    public Long getNumberWarehouse();

    public Page<Warehouse> getAllItemsByPage(Pageable pageable);

    public Warehouse getByProduct(Product product);

    public Page<Warehouse> getByProductCategoryCategoryid(Long id, Pageable pageable);

    public Page<Warehouse> getProductsByNameContainingByPage(String searchText, Pageable pageable);

    public Warehouse getWarehouseByProductProductId(Long productId);

    public Warehouse getWarehouseBySku(String sku);

    public void reducesProductQuantityInStock(Long productId, Integer sold);

    public void deleteWarehose(Warehouse warehouse);
}
