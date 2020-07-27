package it.opensource.ecompany.service.impl;

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
    public Warehouse  getWarehouseByName(String name) {

        return warehouseRepository.findByName(name);
    }

}
