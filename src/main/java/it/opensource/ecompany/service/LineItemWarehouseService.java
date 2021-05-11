package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemsInvoice;

import java.util.Optional;

public interface LineItemWarehouseService {

    long getNumberLineItemWarehouse();

    long getNumberLineItemWarehouseByProductCode(String productCode);

    Optional<LineItemsInvoice> getLineItemWarehouseById(Long lineItemWarehouseId);

}
