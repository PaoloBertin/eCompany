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

    long countByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(Long warehouseId,
                                                                                                       String productCode);

    Page<WarehouseCard> findByDocumentationWarehouseWarehouseId(Long warehouseId, Pageable pageable);

    List<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(Long warehouseId,
                                                                                                                     String productCode);

    Page<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(Long warehouseId,
                                                                                                                     String productCode,
                                                                                                                     Pageable pageable);

    //    Page<WarehouseCard> findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(Long warehouseId,
    //                                                                                                                     String productCode,
    //                                                                                                                     Pageable pageable);

}
