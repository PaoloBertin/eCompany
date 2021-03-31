package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemPurchaseOrder;

import java.util.Optional;

public interface LineItemPurchaseOrderService {

    Optional<LineItemPurchaseOrder> getLineItemPurchaseOrderById(Long lineItemPurchaseOrderId);

}
