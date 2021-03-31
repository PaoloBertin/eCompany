package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemSalesOrder;

import java.util.Optional;

public interface LineItemSalesOrderService {

    Optional<LineItemSalesOrder> getLineItemSalesOrderById(Long lineItemPurchaseOrderId);

}
