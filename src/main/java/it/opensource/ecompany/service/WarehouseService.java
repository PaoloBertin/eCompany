package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> getAllWarehouse();

    Page<Warehouse> getAllWarehousesByPage(Pageable pageable);

    Warehouse getWarehouseById(Long warehouseId);

    Warehouse getWarehouseByName(String name);
}
