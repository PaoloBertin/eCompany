package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {

    long countByDocumentationWarehouseId(Long warehouseId);

    long countByDocumentationWarehouseIdAndDocumentationLineItemProductId(Long warehouseId, Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseId(Long warehouseId, Pageable pageable);

    List<WarehouseCard> findByDocumentationWarehouseIdAndDocumentationLineItemProductId(Long warehouseId, Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseIdAndDocumentationLineItemProductProductCode(Long warehouseId, String productCode,
                                                                                                 Pageable pageable);

    Page<WarehouseCard> findByDocumentationWarehouseIdAndDocumentationLineItemProductId(Long warehouseId, Long productId,
                                                                                        Pageable pageable);

}
