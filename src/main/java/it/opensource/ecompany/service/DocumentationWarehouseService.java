package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.DocumentationWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentationWarehouseService {

    Long getNumberDocumentations();

    Long getNumberDocumentationsBydWarehouseId(Long wareouseId);

    Long getNumberDocumentationsByWarehouseIdAndLineItemProductCode(Long warehouseId, String productCode);

    Page<DocumentationWarehouse> getByWarehouseIdAndLineItemProductCodeByPage(Long warehouseId, String productCode, Pageable pageable);

}
