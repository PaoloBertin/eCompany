package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.repository.WaresRepository;
import it.opensource.ecompany.service.WaresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("waresService")
public class WaresServiceImpl implements WaresService {

    private final WaresRepository waresRepository;

    public WaresServiceImpl(WaresRepository waresRepository) {

        this.waresRepository = waresRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getAllWaresByPage(Pageable pageable) {

        return waresRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable) {

        return waresRepository.findByWarehouseWarehouseid(warehouseId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ware> getBySkuAndWarehouse(String sku, Long warehouseId) {

        return waresRepository.findBySkuAndWarehouseWarehouseid(sku, warehouseId);
    }

    @Transactional(readOnly = true)
    @Override
    public Ware getWareBySku(String sku) {

        return waresRepository.findBySku(sku);
    }

}
