package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Long> {

    public Supplier findByContactEmail(String email);
}
