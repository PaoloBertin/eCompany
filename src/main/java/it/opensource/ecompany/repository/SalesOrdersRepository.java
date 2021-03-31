package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrdersRepository extends JpaRepository<SalesOrder, Long> {

}
