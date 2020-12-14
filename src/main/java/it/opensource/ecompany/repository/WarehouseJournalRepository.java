package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WarehouseJournalRepository extends JpaRepository<WarehouseJournal, Long> {

    Page<WarehouseJournal> findByDocumentationWarehouseJournalWarehouseId(Long id, Pageable pageable);

    Page<WarehouseJournal> findByDocumentationWarehouseJournalWarehouseName(String name, Pageable pageable);

    List<WarehouseJournal> findByDocumentationWarehouseJournalWarehouseIdAndDocumentationWarehouseJournalDocumentDateBetween(
        Long warehouseId, LocalDate documentDateStart, LocalDate documentDateEnd);

    Page<WarehouseJournal> findByDocumentationWarehouseJournalWarehouseIdAndDocumentationWarehouseJournalDocumentDateBetween(
        Long warehouseId, LocalDate documentDateStart, LocalDate documentDateEnd, Pageable pageable);

}
