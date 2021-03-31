package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.SalesOrder;

import java.util.List;

public interface SalesOrdersService {

    List<SalesOrder> getAllSalesOrders();

    SalesOrder getSalesOrderById(Long salesOrderId);

}
