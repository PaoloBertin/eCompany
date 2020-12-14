package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.DocumentationWarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentationWarehouseCardService {

    Long getNumberDocumentations();

    Long getNumberDocumentationsBydWarehouseId(Long wareouseId);

    Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    Page<DocumentationWarehouseCard> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable);


}
