package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {

    long countByDocumentationWarehouseCardWarehouseId(Long warehouseId);

    long countByDocumentationWarehouseCardWarehouseIdAndDocumentationWarehouseCardLineItemWarehouseProductId(Long warehouseId,
                                                                                                             Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseCardWarehouseId(Long warehouseId, Pageable pageable);

    List<WarehouseCard> findByDocumentationWarehouseCardWarehouseIdAndDocumentationWarehouseCardLineItemWarehouseProductId(Long warehouseId,
                                                                                                                           Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseCardWarehouseIdAndDocumentationWarehouseCardLineItemWarehouseProductProductCode(
        Long warehouseId, String productCode, Pageable pageable);

    Page<WarehouseCard> findByDocumentationWarehouseCardWarehouseIdAndDocumentationWarehouseCardLineItemWarehouseProductId(Long warehouseId,
                                                                                                                           Long productId,
                                                                                                                           Pageable pageable);

}
