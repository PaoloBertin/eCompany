package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.repository.WarehouseRepository;
import it.opensource.ecompany.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {

        this.warehouseRepository = warehouseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Warehouse> getAllWarehouse() {

        return warehouseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getAllWarehousesByPage(Pageable pageable) {

        return warehouseRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Warehouse getWarehouseById(Long warehouseId) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                                                 .orElse(null);
        return warehouse;
    }

    @Transactional(readOnly = true)
    @Override
    public Warehouse getWarehouseByName(String name) {

        return warehouseRepository.findByName(name);
    }

    @Override
    public List<Warehouse> getByWaresIdIn(Collection<Long> wareId) {

        return warehouseRepository.findByWaresIdIn(wareId);
    }

    @Override
    public List<Warehouse> getByWaresSkuIn(Collection<String> sku) {

        return warehouseRepository.findByWaresSkuIn(sku);
    }

}
