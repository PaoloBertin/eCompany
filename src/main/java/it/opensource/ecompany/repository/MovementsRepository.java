package it.opensource.ecompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.opensource.ecompany.domain.Movement;

@Repository
public interface MovementsRepository extends JpaRepository<Movement, Long> {

    public List<Movement> findByCustomerCustomerid(Long id);
}
