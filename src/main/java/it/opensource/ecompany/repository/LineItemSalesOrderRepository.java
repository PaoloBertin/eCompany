package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.LineItemPurchaseOrder;
import it.opensource.ecompany.domain.LineItemSalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemSalesOrderRepository extends JpaRepository<LineItemSalesOrder, Long> {

}
