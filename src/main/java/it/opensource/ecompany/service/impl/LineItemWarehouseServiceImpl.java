package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemWarehouse;
import it.opensource.ecompany.repository.LineItemWarehouseRepository;
import it.opensource.ecompany.service.LineItemWarehouseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lineItemWarehouseService")
public class LineItemWarehouseServiceImpl implements LineItemWarehouseService {

    private final LineItemWarehouseRepository lineItemWarehouseRepository;

    public LineItemWarehouseServiceImpl(LineItemWarehouseRepository lineItemWarehouseRepository) {

        this.lineItemWarehouseRepository = lineItemWarehouseRepository;
    }

    @Override
    public long getNumberLineItemWarehouse() {

        return lineItemWarehouseRepository.count();
    }

    @Override
    public long getNumberLineItemWarehouseByProduct(Long productId) {

        return lineItemWarehouseRepository.countByProductId(productId);
    }

    @Override
    public Optional<LineItemWarehouse> getLineItemWarehouseById(Long lineItemWarhouseId) {

        return lineItemWarehouseRepository.findById(lineItemWarhouseId);
    }

}
