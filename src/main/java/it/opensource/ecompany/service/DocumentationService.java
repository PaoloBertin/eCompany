package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Documentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentationService {

    Long getNumberDocumentations();

    Long getNumberDocumentationsBydWarehouseId(Long wareouseId);

    Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    Page<Documentation> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable);


}
