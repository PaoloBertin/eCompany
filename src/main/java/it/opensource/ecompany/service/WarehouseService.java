package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    Long getWarehouseNumber();

    List<Warehouse> getAllWarehouse();

    Page<Warehouse> getAllWarehousesByPage(Pageable pageable);

    Optional<Warehouse> getWarehouseById(Long warehouseId);

    Optional<Warehouse> getWarehouseByName(String name);

    Warehouse saveWarehouse(Warehouse warehouse);

    void deleteWarehouse(Long warehouseId);

}
