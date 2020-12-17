package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.DocumentationWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationWarehouseRepository extends JpaRepository<DocumentationWarehouse, Long> {

    Long countByWarehouseId(Long warehouseId);

    Long countByWarehouseIdAndLineItemWarehouseProductCode(Long warehouseId, String productCode);

    Page<DocumentationWarehouse> findByWarehouseIdAndLineItemWarehouseProductCode(Long warehouseId, String productCode, Pageable pageable);

}
