package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterprisesRepository extends JpaRepository<Enterprise, Long> {

}
