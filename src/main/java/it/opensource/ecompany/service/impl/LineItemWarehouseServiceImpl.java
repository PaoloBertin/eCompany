package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemWarehouse;
import it.opensource.ecompany.repository.LineItemWarehouseRepository;
import it.opensource.ecompany.service.LineItemWarehouseService;
import it.opensource.ecompany.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lineItemWarehouseService")
public class LineItemWarehouseServiceImpl implements LineItemWarehouseService {

    private final LineItemWarehouseRepository lineItemWarehouseRepository;

    private final ProductsService productsService;

    public LineItemWarehouseServiceImpl(LineItemWarehouseRepository lineItemWarehouseRepository, ProductsService productsService) {

        this.lineItemWarehouseRepository = lineItemWarehouseRepository;
        this.productsService = productsService;
    }

    @Override
    public long getNumberLineItemWarehouse() {

        return lineItemWarehouseRepository.count();
    }

    @Override
    public long getNumberLineItemWarehouseByProductCode(String productCode) {

        return lineItemWarehouseRepository.countByProductCode(productCode);
    }

    @Override
    public Optional<LineItemWarehouse> getLineItemWarehouseById(Long lineItemWarhouseId) {

        return lineItemWarehouseRepository.findById(lineItemWarhouseId);
    }

}
