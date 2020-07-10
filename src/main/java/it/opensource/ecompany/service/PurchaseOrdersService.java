package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrdersService {

    public List<PurchaseOrder> getAllPurchaseOrders();

    public PurchaseOrder getPurchaseOrderById(Long id);

    public List<PurchaseOrder> getPurchaseOrderByCustomer(Long customerid);

    public void savePurchaseOrder();

}
