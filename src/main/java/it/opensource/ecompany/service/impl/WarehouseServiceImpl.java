package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.repository.WarehouseRepository;
import it.opensource.ecompany.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    //    private final WarehouseRepository warehouseRepository;

    @Override
    public Page<Warehouse> getAllItemsByPage(Pageable pageable) {

        return warehouseRepository.findAll(pageable);
    }

    @Override
    public Page<Warehouse> getByProductCategoryCategoryid(Long id, Pageable pageable) {

        return warehouseRepository.findByProductCategoryCategoryid(id, pageable);
    }

    @Override
    public Page<Warehouse> getProductsByNameContainingByPage(String searchText, Pageable pageable) {

        return warehouseRepository.findByProductNameContaining(searchText, pageable);
//        return warehouseRepository.findByProductName(searchText, pageable);
    }

    @Override
    public Warehouse getWarehouseBySku(String sku) {

        return warehouseRepository.findBySku(sku);
    }
}
