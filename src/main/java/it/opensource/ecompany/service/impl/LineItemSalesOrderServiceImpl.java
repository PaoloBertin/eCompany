package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemSalesOrder;
import it.opensource.ecompany.repository.LineItemSalesOrderRepository;
import it.opensource.ecompany.service.LineItemSalesOrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lineItemPurchaseOrderService")
public class LineItemSalesOrderServiceImpl implements LineItemSalesOrderService {

    private final LineItemSalesOrderRepository lineItemSalesOrderRepository;

    public LineItemSalesOrderServiceImpl(LineItemSalesOrderRepository lineItemSalesOrderRepository) {

        this.lineItemSalesOrderRepository = lineItemSalesOrderRepository;
    }

    @Override
    public Optional<LineItemSalesOrder> getLineItemSalesOrderById(Long lineItemSalesOrderId) {

        return lineItemSalesOrderRepository.findById(lineItemSalesOrderId);
    }

}
