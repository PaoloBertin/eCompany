package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.DocumentationWarehouse;
import it.opensource.ecompany.repository.DocumentationWarehouseRepository;
import it.opensource.ecompany.service.DocumentationWarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("documentationWarehouseCardService")
public class DocumentationWarehouseServiceImpl implements DocumentationWarehouseService {

    private DocumentationWarehouseRepository documentationWarehouseRepository;

    public DocumentationWarehouseServiceImpl(DocumentationWarehouseRepository documentationWarehouseRepository) {

        this.documentationWarehouseRepository = documentationWarehouseRepository;
    }

    @Override
    public Long getNumberDocumentations() {

        return documentationWarehouseRepository.count();
    }

    @Override
    public Long getNumberDocumentationsBydWarehouseId(Long wareouseId) {

//        return documentationWarehouseRepository.countByWarehouseId(wareouseId);
        return null;
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductCode(Long warehouseId, String productCode) {

//        return documentationWarehouseRepository.countByWarehouseIdAndLineItemWarehouseProductCode(warehouseId, productCode);
        return null;
    }

    @Override
    public Page<DocumentationWarehouse> getByWarehouseIdAndLineItemProductCodeByPage(Long warehouseId, String productCode,
                                                                                   Pageable pageable) {

//        return documentationWarehouseRepository.countByWarehouseIdAndLineItemWarehouseProductCode(warehouseId, productCode);
        return null;
    }

}
