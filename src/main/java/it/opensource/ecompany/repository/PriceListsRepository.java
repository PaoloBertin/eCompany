package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListsRepository extends JpaRepository<PriceList, Long> {

}
