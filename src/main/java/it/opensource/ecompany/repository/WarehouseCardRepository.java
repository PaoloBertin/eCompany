package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {

    long countByDocumentationWarehouseWarehouseId(Long warehouseId);

    long countByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(Long warehouseId,
                                                                                                             Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseWarehouseId(Long warehouseId, Pageable pageable);

    List<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(Long warehouseId,
                                                                                                                           Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductProductCode(
        Long warehouseId, String productCode, Pageable pageable);

    Page<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(Long warehouseId,
                                                                                                                           Long productId,
                                                                                                                           Pageable pageable);

}
