package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.SalesOrder;
import it.opensource.ecompany.repository.SalesOrdersRepository;
import it.opensource.ecompany.service.SalesOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("salesOrdersService")
public class SalesOrdersServiceImpl implements SalesOrdersService {

    private final SalesOrdersRepository salesOrdersRepository;

    public SalesOrdersServiceImpl(SalesOrdersRepository salesOrdersRepository) {

        this.salesOrdersRepository = salesOrdersRepository;
    }

    @Override
    public List<SalesOrder> getAllSalesOrders() {

        return salesOrdersRepository.findAll();
    }

    @Override
    public SalesOrder getSalesOrderById(Long salesOrderId) {

        return salesOrdersRepository.findById(salesOrderId).get();
    }
}
