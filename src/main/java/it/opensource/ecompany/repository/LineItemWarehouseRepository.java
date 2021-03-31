package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.LineItemWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemWarehouseRepository extends JpaRepository<LineItemWarehouse, Long> {

    long countByProductCode(String productCode);

}
