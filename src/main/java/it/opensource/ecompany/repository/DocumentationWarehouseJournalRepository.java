package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.DocumentationWarehouseJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationWarehouseJournalRepository extends JpaRepository<DocumentationWarehouseJournal, Long> {

    Long countByWarehouseId(Long warehouseId);

    Long countByWarehouseIdAndLineItemWarehouseJournalProductId(Long warehouseId, Long productId);

    Page<DocumentationWarehouseJournal> findByWarehouseIdAndLineItemWarehouseJournalProductId(Long warehouseId, Long productId, Pageable pageable);

}
