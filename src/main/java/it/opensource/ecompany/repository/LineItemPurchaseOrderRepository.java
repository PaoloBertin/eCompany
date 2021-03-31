package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.LineItemPurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemPurchaseOrderRepository extends JpaRepository<LineItemPurchaseOrder, Long> {

}
