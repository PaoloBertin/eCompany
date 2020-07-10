package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.*;
import it.opensource.ecompany.repository.PurchaseOrdersRepository;
import it.opensource.ecompany.service.PurchaseOrdersService;
import it.opensource.ecompany.service.UserContext;
import it.opensource.ecompany.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

    private final CartBean cartBean;

    private final UserContext userContext;

    private final PurchaseOrdersRepository purchaseOrdersRepository;

    private final WarehouseService warehouseService;

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {

        return purchaseOrdersRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseOrder getPurchaseOrderById(Long purchaseOrderId) {

        return purchaseOrdersRepository.findById(purchaseOrderId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseOrder> getPurchaseOrderByCustomer(Long customerId) {

        return purchaseOrdersRepository.findByCustomerCustomerid(customerId);
    }

    @Override
    public void savePurchaseOrder() {

        Customer customer = userContext.getCurrentCustomer();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDateMovement(new Date());
        purchaseOrder.setTotalAmount(cartBean.getTotalCost());
        purchaseOrder.setState(State.nuovo);
        purchaseOrder.setCustomer(customer);
        // costruzione lista LineItem associata all'acquisto
        List<Lineitem> lineitems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartBean.getProducts().entrySet()) {
            Lineitem lineitem = new Lineitem();
            lineitem.setProduct(entry.getKey());
            lineitem.setQuantity(Double.valueOf(entry.getValue()));
            lineitems.add(lineitem);
        }
        purchaseOrder.setLineitems(lineitems);
        purchaseOrdersRepository.save(purchaseOrder);

        // scala prodotti dal magazzino
        for (Map.Entry<Product, Integer> entry : cartBean.getProducts().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            warehouseService.reducesProductQuantityInStock(product.getProductid(), quantity);
        }

    }
}
