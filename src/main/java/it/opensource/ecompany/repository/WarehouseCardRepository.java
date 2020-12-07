package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {

    long countByWarehouseId(Long warehouseId);

    long countByWarehouseIdAndLineItemProductProductid(Long warehouseId, Long productId);

    Page<WarehouseCard> findByWarehouseId(Long id, Pageable pageable);

    List<WarehouseCard> findByWarehouseIdAndLineItemProductProductid(Long warehouseId, Long productId);

    Page<WarehouseCard> findByWarehouseIdAndLineItemProductProductid(Long warehouseId, Long productId, Pageable pageable);

}
