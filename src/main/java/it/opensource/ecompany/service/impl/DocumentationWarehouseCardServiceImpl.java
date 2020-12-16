package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.DocumentationWarehouseCard;
import it.opensource.ecompany.repository.DocumentationWarehouseCardRepository;
import it.opensource.ecompany.service.DocumentationWarehouseCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("documentationWarehouseCardService")
public class DocumentationWarehouseCardServiceImpl implements DocumentationWarehouseCardService {

    private DocumentationWarehouseCardRepository documentationWarehouseCardRepository;

    public DocumentationWarehouseCardServiceImpl(DocumentationWarehouseCardRepository documentationWarehouseCardRepository) {

        this.documentationWarehouseCardRepository = documentationWarehouseCardRepository;
    }

    @Override
    public Long getNumberDocumentations() {

        return documentationWarehouseCardRepository.count();
    }

    @Override
    public Long getNumberDocumentationsBydWarehouseId(Long wareouseId) {

        return documentationWarehouseCardRepository.countByWarehouseId(wareouseId);
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return documentationWarehouseCardRepository.countByWarehouseIdAndLineItemWarehouseProductId(warehouseId, productId);
    }

    @Override
    public Page<DocumentationWarehouseCard> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId,
                                                                                       Pageable pageable) {

        return documentationWarehouseCardRepository.findByWarehouseIdAndLineItemWarehouseProductId(warehouseId, productId, pageable);
    }

}
