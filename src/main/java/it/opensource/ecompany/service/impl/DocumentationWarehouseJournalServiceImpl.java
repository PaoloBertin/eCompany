package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.DocumentationWarehouseJournal;
import it.opensource.ecompany.repository.DocumentationWarehouseJournalRepository;
import it.opensource.ecompany.service.DocumentationWarehouseJournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("documentationService")
public class DocumentationWarehouseJournalServiceImpl implements DocumentationWarehouseJournalService {

    private DocumentationWarehouseJournalRepository documentationWarehouseJournalRepository;

    public DocumentationWarehouseJournalServiceImpl(DocumentationWarehouseJournalRepository documentationWarehouseJournalRepository) {

        this.documentationWarehouseJournalRepository = documentationWarehouseJournalRepository;
    }

    @Override
    public Long getNumberDocumentations() {

        return documentationWarehouseJournalRepository.count();
    }

    @Override
    public Long getNumberDocumentationsBydWarehouseId(Long wareouseId) {

        return documentationWarehouseJournalRepository.countByWarehouseId(wareouseId);
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return documentationWarehouseJournalRepository.countByWarehouseIdAndLineItemWarehouseJournalProductId(warehouseId, productId);
    }

    @Override
    public Page<DocumentationWarehouseJournal> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable) {

        return documentationWarehouseJournalRepository.findByWarehouseIdAndLineItemWarehouseJournalProductId(warehouseId, productId, pageable);
    }

}
