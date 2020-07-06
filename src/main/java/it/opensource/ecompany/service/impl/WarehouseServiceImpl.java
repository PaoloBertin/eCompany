package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.repository.WarehouseRepository;
import it.opensource.ecompany.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getAllItemsByPage(Pageable pageable) {

        return warehouseRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getByProductCategoryCategoryid(Long id, Pageable pageable) {

        return warehouseRepository.findByProductCategoryCategoryid(id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Warehouse getWarehouseByProductProductId(Long productId) {

        return warehouseRepository.findByProductProductid(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getProductsByNameContainingByPage(String searchText, Pageable pageable) {

        return warehouseRepository.findByProductNameContaining(searchText, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Warehouse getWarehouseBySku(String sku) {

        return warehouseRepository.findBySku(sku);
    }

    @Override
    public void reducesProductQuantityInStock(Long productId, Integer sold) {

        Warehouse warehouse = warehouseRepository.findByProductProductid(productId);

        Integer charge = warehouse.getQuantity();
        warehouse.setQuantity(charge - sold);
        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehose(Warehouse warehouse) {

        warehouseRepository.delete(warehouse);
    }

    @Override
    public Warehouse getByProduct(Product product) {

        return warehouseRepository.findByProduct(product);
    }

    @Override
    public Long getNumberWarehouse() {

        return warehouseRepository.count();
    }

}
