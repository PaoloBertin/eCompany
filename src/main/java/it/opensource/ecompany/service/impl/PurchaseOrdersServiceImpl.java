package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.*;
import it.opensource.ecompany.repository.PurchaseOrdersRepository;
import it.opensource.ecompany.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

    private final CartBean cartBean;

    private final UserContext userContext;

    private final PurchaseOrdersRepository purchaseOrdersRepository;

    private final WarehouseCardService warehouseCardService;

    private final AccountsService accountsService;

    public PurchaseOrdersServiceImpl(CartBean cartBean, UserContext userContext, PurchaseOrdersRepository purchaseOrdersRepository,
                                     WarehouseCardService warehouseCardService, AccountsService accountsService) {

        this.cartBean = cartBean;
        this.userContext = userContext;
        this.purchaseOrdersRepository = purchaseOrdersRepository;
        this.warehouseCardService = warehouseCardService;
        this.accountsService = accountsService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {

        return purchaseOrdersRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseOrder getPurchaseOrderById(Long purchaseOrderId) {

        return purchaseOrdersRepository.findById(purchaseOrderId)
                                       .get();
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
        purchaseOrder.setDatePurchase(new Date());
        purchaseOrder.setTotalAmount(cartBean.getTotalCost());
        purchaseOrder.setState(State.nuovo);
        purchaseOrder.setCustomer(customer);

        // costruzione lista LineItem associata all'acquisto
        List<LineItem> lineitems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartBean.getProducts()
                                                         .entrySet()) {
            LineItem lineitem = new LineItem();
            lineitem.setProduct(entry.getKey());
            lineitem.setQuantity(Double.valueOf(entry.getValue()));
            lineitems.add(lineitem);
        }
        purchaseOrder.setLineitems(lineitems);

        // salva ordine
        purchaseOrdersRepository.save(purchaseOrder);

        // scala prodotti dal magazzino
        Long warehouseId = 1L;
        for (Map.Entry<Product, Integer> entry : cartBean.getProducts()
                                                         .entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Long productId = product.getId();
//            warehouseCardService.productsWithdrawalFromWarehouse(warehouseId, productId, quantity);
        }

        // aggiunge fattura al conto
        Long accountId = 1L;
        Double totalAmount = purchaseOrder.getTotalAmount();
        accountsService.deposit(accountId, BigDecimal.valueOf(totalAmount));
    }
}
