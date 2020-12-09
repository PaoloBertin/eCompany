package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Documentation;
import it.opensource.ecompany.repository.DocumentationRepository;
import it.opensource.ecompany.service.DocumentationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("documentationService")
public class DocumentationServiceImpl implements DocumentationService {

    private DocumentationRepository documentationRepository;

    public DocumentationServiceImpl(DocumentationRepository documentationRepository) {

        this.documentationRepository = documentationRepository;
    }

    @Override
    public Long getNumberDocumentations() {

        return documentationRepository.count();
    }

    @Override
    public Long getNumberDocumentationsBydWarehouseId(Long wareouseId) {

        return documentationRepository.countByWarehouseId(wareouseId);
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return documentationRepository.countByWarehouseIdAndLineItemProductId(warehouseId, productId);
    }

    @Override
    public Page<Documentation> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId, Pageable pageable) {

        return documentationRepository.findByWarehouseIdAndLineItemProductId(warehouseId, productId, pageable);
    }

}
