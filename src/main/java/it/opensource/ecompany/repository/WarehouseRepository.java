package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Warehouse findByName(String name);

    List<Warehouse> findByWaresIdIn(Collection<Long> wareId);

    List<Warehouse> findByWaresSkuIn(Collection<String> sku);

}
