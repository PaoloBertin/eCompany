package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.DocumentationWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentationWarehouseService {

    Long getNumberDocumentations();

    Long getNumberDocumentationsBydWarehouseId(Long wareouseId);

    Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    Page<DocumentationWarehouse> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable);


}
