package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.LineItemWarehouseJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemWarehouseJournalRepository extends JpaRepository<LineItemWarehouseJournal, Long> {

}
