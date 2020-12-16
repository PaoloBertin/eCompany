package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.repository.WarehouseCardRepository;
import it.opensource.ecompany.service.WarehouseCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service("warehouseCardService")
public class WarehouseCardServiceImpl implements WarehouseCardService {

    private final WarehouseCardRepository warehouseCardRepository;

    public WarehouseCardServiceImpl(WarehouseCardRepository warehouseCardRepository) {

        this.warehouseCardRepository = warehouseCardRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getNumberWarehouseCards() {

        return warehouseCardRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getNumberWarehouseCardsBydWarehouseId(Long wareouseId) {

        return warehouseCardRepository.countByDocumentationWarehouseWarehouseId(wareouseId);

    }

    @Transactional(readOnly = true)
    @Override
    public Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return warehouseCardRepository.countByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(
            warehouseId, productId);
    }

    @Override
    public Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId) {

        return warehouseCardRepository.findById(warehouseCardId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WarehouseCard> getAllWarehouseCards() {

        return warehouseCardRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getAllWarehouseCardsByPage(Pageable pageable) {

        return warehouseCardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getWarehouseCardsByWarehouseByPage(Long warehouseId, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseId(warehouseId, pageable);
    }

    @Override
    public List<WarehouseCard> getWarehouseCardsByWarehouseIdAndProductId(Long warehouseId, Long productId) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(
            warehouseId, productId);
    }

    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIdByPage(Long warehouseId, Long productId, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductId(
            warehouseId, productId, pageable);
    }

    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIsbn(Long warehouseId, String productCode, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductProductCode(
            warehouseId, productCode, pageable);
    }


}
