package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.DocumentationWarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationWarrehouseCardRepository extends JpaRepository<DocumentationWarehouseCard, Long> {

    Long countByWarehouseId(Long warehouseId);

    Long countByWarehouseIdAndLineItemWarehouseCardProductId(Long warehouseId, Long productId);

    Page<DocumentationWarehouseCard> findByWarehouseIdAndLineItemWarehouseCardProductId(Long warehouseId, Long productId, Pageable pageable);

}
