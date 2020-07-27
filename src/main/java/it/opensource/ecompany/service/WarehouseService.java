package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    public Page<Warehouse> getAllWarehousesByPage(Pageable pageable);

    public Warehouse getWarehouseById(Long warehouseId);

    public Warehouse getWarehouseByName(String name);
}
