package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.repository.WaresRepository;
import it.opensource.ecompany.service.WaresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("waresService")
public class WaresServiceImpl implements WaresService {

    private final WaresRepository waresRepository;

    public WaresServiceImpl(WaresRepository waresRepository) {

        this.waresRepository = waresRepository;
    }

    @Override
    public Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable) {

        return waresRepository.findByWarehouseWarehouseid(warehouseId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getWaresByCategoryCategoryid(Long warehouseId, Long categoryId, Pageable pageable) {

        return waresRepository.findByWarehouseWarehouseidAndProductCategoryCategoryid(warehouseId, categoryId,
                                                                                      pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getByWarehouseWarehouseidAndProductNameContaining(Long warehouseId, String searchText,
                                                                        Pageable pageable) {

        return waresRepository.findByWarehouseWarehouseidAndProductNameContaining(warehouseId, searchText, pageable);
    }

    @Override
    public Ware getWareBySku(String sku) {

        return waresRepository.findBySku(sku);
    }
}
