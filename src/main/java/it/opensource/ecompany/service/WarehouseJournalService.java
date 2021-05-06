package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.WarehouseJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WarehouseJournalService {

    List<WarehouseJournal> getAllWarehouseJurnal();

    Page<WarehouseJournal> getAllWarehouseJournalByPage(Pageable pageable);

    Page<WarehouseJournal> getWarehouseJournalsByWarehouseId(Long warehouseId, Pageable pageable);

    Page<WarehouseJournal> getByWarehouseName(String name, Pageable pageable);

    List<WarehouseJournal> getAllWarehouseJournalByDocumentDateBetween(Long warehouseId, LocalDate documentDateStart,
                                                                       LocalDate documentDateEnd);

    Page<WarehouseJournal> getAllWarehouseJournalByDocumentDateBetween(Long warehouseId, LocalDate documentDateStart,
                                                                       LocalDate documentDateEnd, Pageable pageable);

    Optional<WarehouseJournal> getWarehouseJournalById(Long warehouseId);

    WarehouseJournal saveWarehouseJournal(WarehouseJournal warehouseJournal);

    void deleteWarehouseJournal(Long warehouseJournalId);
}
