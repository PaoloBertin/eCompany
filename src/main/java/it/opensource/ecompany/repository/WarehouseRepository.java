package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    public Page<Warehouse> findByProductCategoryCategoryid(Long categoryid, Pageable pageable);

    public Page<Warehouse> findByProductNameContaining(String searchText, Pageable pageable);

    public Warehouse findBySku(String sku);
}
