package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.domain.util.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WarehouseJournalRepository extends JpaRepository<WarehouseJournal, Long> {

    Page<WarehouseJournal> findByWarehouseId(Long warehouseId, Pageable pageable);

    Page<WarehouseJournal> findByWarehouseIdAndDocumentType(Long warehouseId, Document documentType, Pageable pageable);

    WarehouseJournal findByWarehouseIdAndDocumentNumber(Long warehouseId, String documentNumber);

}
