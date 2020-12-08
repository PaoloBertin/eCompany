package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.repository.WarehouseCardRepository;
import it.opensource.ecompany.service.WarehouseCardService;
import it.opensource.ecompany.service.dto.WareDto;
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

        return warehouseCardRepository.countByDocumentationWarehouseId(wareouseId);

    }

    @Transactional(readOnly = true)
    @Override
    public Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId) {

        return warehouseCardRepository.countByDocumentationWarehouseIdAndDocumentationLineItemProductProductid(warehouseId, productId);
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

        return warehouseCardRepository.findByDocumentationWarehouseId(warehouseId, pageable);
    }

    @Override
    public List<WarehouseCard> getWarehouseCardsByWarehouseIdAndProductId(Long warehouseId, Long productId) {

        return warehouseCardRepository.findByDocumentationWarehouseIdAndDocumentationLineItemProductProductid(warehouseId, productId);
    }

    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIdByPage(Long warehouseId, Long productId, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseIdAndDocumentationLineItemProductProductid(warehouseId, productId,
                                                                                                              pageable);
    }

    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIsbn(Long warehouseId, String isbn, Pageable pageable) {

        return warehouseCardRepository.findByDocumentationWarehouseIdAndDocumentationLineItemProductIsbn(warehouseId, isbn, pageable);
    }

    @Override
    public Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId) {

        return warehouseCardRepository.findById(warehouseCardId);
    }

}
