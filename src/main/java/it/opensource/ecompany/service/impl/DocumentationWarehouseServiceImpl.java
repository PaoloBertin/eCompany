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

        return documentationWarehouseRepository.countByWarehouseId(wareouseId);
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return documentationWarehouseRepository.countByWarehouseIdAndLineItemWarehouseProductId(warehouseId, productId);
    }

    @Override
    public Page<DocumentationWarehouse> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId,
                                                                                   Pageable pageable) {

        return documentationWarehouseRepository.findByWarehouseIdAndLineItemWarehouseProductId(warehouseId, productId, pageable);
    }

}
