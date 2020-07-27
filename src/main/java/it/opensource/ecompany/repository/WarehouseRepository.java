package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    public Warehouse findByName(String name);

}
