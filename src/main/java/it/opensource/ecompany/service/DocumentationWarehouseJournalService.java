package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.DocumentationWarehouseJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentationWarehouseJournalService {

    Long getNumberDocumentations();

    Long getNumberDocumentationsBydWarehouseId(Long wareouseId);

    Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    Page<DocumentationWarehouseJournal> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable);


}
