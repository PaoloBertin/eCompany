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

    long countByDocumentationWarehouseIdAndDocumentationLineItemProductProductid(Long warehouseId, Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseId(Long warehouseId, Pageable pageable);

    List<WarehouseCard> findByDocumentationWarehouseIdAndDocumentationLineItemProductProductid(Long warehouseId, Long productId);

    Page<WarehouseCard> findByDocumentationWarehouseIdAndDocumentationLineItemProductIsbn(Long warehouseId, String isbn, Pageable pageable);
}
