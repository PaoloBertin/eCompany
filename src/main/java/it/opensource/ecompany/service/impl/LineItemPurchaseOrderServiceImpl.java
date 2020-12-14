package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemPurchaseOrder;
import it.opensource.ecompany.repository.LineItemPurchaseOrderRepository;
import it.opensource.ecompany.service.LineItemPurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("LineItemPurchaseOrderService")
public class LineItemPurchaseOrderServiceImpl implements LineItemPurchaseOrderService {

    private final LineItemPurchaseOrderRepository lineItemPurchaseOrderRepository;

    public LineItemPurchaseOrderServiceImpl(LineItemPurchaseOrderRepository lineItemPurchaseOrderRepository) {

        this.lineItemPurchaseOrderRepository = lineItemPurchaseOrderRepository;
    }

    @Override
    public Optional<LineItemPurchaseOrder> getLineItemPurchaseOrderById(Long lineItemId) {

        return lineItemPurchaseOrderRepository.findById(lineItemId);
    }

}
