package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Documentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    Long countByWarehouseId(Long warehouseId);

    Long countByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    Page<Documentation> findByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId, Pageable pageable);

}
