package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.repository.WarehouseRepository;
import it.opensource.ecompany.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {

        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Long getWarehouseNumber() {

        return warehouseRepository.count();
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
    public Optional<Warehouse> getWarehouseById(Long warehouseId) {

        return warehouseRepository.findById(warehouseId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Warehouse> getWarehouseByName(String name) {

        return warehouseRepository.findByName(name);
    }

    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(Long warehouseId) {

        warehouseRepository.deleteById(warehouseId);
    }

}
