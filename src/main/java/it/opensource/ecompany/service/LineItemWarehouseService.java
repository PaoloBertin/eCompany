package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemWarehouse;

import java.util.Optional;

public interface LineItemWarehouseService {

    long getNumberLineItemWarehouse();

    long getNumberLineItemWarehouseByProduct(Long productId);

    Optional<LineItemWarehouse> getLineItemWarehouseById(Long lineItemWarehouseId);

}
