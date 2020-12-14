package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.LineItemWarehouseCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemWarehouseCardRepository extends JpaRepository<LineItemWarehouseCard, Long> {

}
