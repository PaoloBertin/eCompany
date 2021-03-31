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
    public Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(Long warehouseId, String productCode) {

        return warehouseCardRepository.countByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(
            warehouseId, productCode);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public List<WarehouseCard> getWarehouseCardsByWarehouseIdAndProductCode(Long warehouseId, String productCode) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(
            warehouseId, productCode);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductCodeByPage(Long warehouseId, String productCode, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(
            warehouseId, productCode, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIsbn(Long warehouseId, String productCode, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseWarehouseIdAndDocumentationWarehouseLineItemWarehouseProductCode(
            warehouseId, productCode, pageable);
    }


}
