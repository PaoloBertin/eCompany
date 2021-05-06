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

        // TODO
        return null;

    }

    @Transactional(readOnly = true)
    @Override
    public Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(Long warehouseId, String productCode) {

        // TODO
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId) {

        // TODO
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WarehouseCard> getAllWarehouseCards() {

        // TODO
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getAllWarehouseCardsByPage(Pageable pageable) {

        return warehouseCardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getAllWarehouseCardsByWarehouseIdByPage(Long warehouseId, Pageable pageable) {

        return warehouseCardRepository.findByWarehouseId(warehouseId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getAllWarehouseCardsByWarehouseIdAndProductCode(Long warehouseId, String productCode, Pageable pageable) {

        return warehouseCardRepository.findByWarehouseIdAndProductProductCode(warehouseId, productCode, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductCodeByPage(Long warehouseId, String productCode, Pageable pageable) {

        // TODO
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseCard> getByWarehouseIdAndProductIsbn(Long warehouseId, String productCode, Pageable pageable) {

        // TODO
        return null;
    }
}
