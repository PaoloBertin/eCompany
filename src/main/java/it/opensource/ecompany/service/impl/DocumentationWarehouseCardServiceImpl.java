package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.DocumentationWarehouseCard;
import it.opensource.ecompany.repository.DocumentationWarrehouseCardRepository;
import it.opensource.ecompany.service.DocumentationWarehouseCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("documentationWarehouseCardService")
public class DocumentationWarehouseCardServiceImpl implements DocumentationWarehouseCardService {

    private DocumentationWarrehouseCardRepository documentationWarrehouseCardRepository;

    public DocumentationWarehouseCardServiceImpl(DocumentationWarrehouseCardRepository documentationWarrehouseCardRepository) {

        this.documentationWarrehouseCardRepository = documentationWarrehouseCardRepository;
    }

    @Override
    public Long getNumberDocumentations() {

        return documentationWarrehouseCardRepository.count();
    }

    @Override
    public Long getNumberDocumentationsBydWarehouseId(Long wareouseId) {

        return documentationWarrehouseCardRepository.countByWarehouseId(wareouseId);
    }

    @Override
    public Long getNumberDocumentationsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return documentationWarrehouseCardRepository.countByWarehouseIdAndLineItemWarehouseCardProductId(warehouseId, productId);
    }

    @Override
    public Page<DocumentationWarehouseCard> getByWarehouseIdAndLineItemProductIdByPage(Long warehouseId, Long productId,
                                                                                       Pageable pageable) {

        return documentationWarrehouseCardRepository.findByWarehouseIdAndLineItemWarehouseCardProductId(warehouseId, productId, pageable);
    }

}
