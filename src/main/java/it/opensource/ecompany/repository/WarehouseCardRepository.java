package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {

    Page<WarehouseCard> findByWarehouseId(Long warehouseId, Pageable pageable);

    Page<WarehouseCard> findByWarehouseIdAndProductProductCode(Long warehouseId, String productCode, Pageable pageable);
}
