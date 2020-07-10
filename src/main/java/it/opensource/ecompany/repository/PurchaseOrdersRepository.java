package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrder, Long> {

    public List<PurchaseOrder> findByCustomerCustomerid(Long id);
}
