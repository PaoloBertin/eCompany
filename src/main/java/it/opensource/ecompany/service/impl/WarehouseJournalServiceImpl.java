package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.repository.WarehouseJournalRepository;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service("warehouseJournalService")
public class WarehouseJournalServiceImpl implements WarehouseJournalService {

    private final WarehouseJournalRepository warehouseJournalRepository;

    public WarehouseJournalServiceImpl(WarehouseJournalRepository warehouseJournalRepository) {

        this.warehouseJournalRepository = warehouseJournalRepository;
    }

    @Override
    public Optional<WarehouseJournal> getWarehouseJournalById(Long warehouseId) {

        return warehouseJournalRepository.findById(warehouseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WarehouseJournal> getAllWarehouseJurnal() {

        return warehouseJournalRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseJournal> getAllWarehouseJournalByPage(Pageable pageable) {

        return warehouseJournalRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseJournal> getWarehouseJournalsByWarehouseId(Long warehouseId, Pageable pageable) {

        return warehouseJournalRepository.findByWarehouseId(warehouseId,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseJournal> getByWarehouseName(String name, Pageable pageable) {

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WarehouseJournal> getAllWarehouseJournalByDocumentDateBetween(Long warehouseId, LocalDate documentDateStart,
                                                                              LocalDate documentDateEnd) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseJournal> getAllWarehouseJournalByDocumentDateBetween(Long warehouseId, LocalDate documentDateStart,
                                                                              LocalDate documentDateEnd, Pageable pageable) {

        return null;
    }

    @Override
    public WarehouseJournal saveWarehouseJournal(WarehouseJournal warehouseJournal) {

        return warehouseJournalRepository.save(warehouseJournal);
    }

    @Override
    public void deleteWarehouseJournal(Long warehouseJournalId) {

        warehouseJournalRepository.deleteById(warehouseJournalId);
    }

}
