package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Warehouse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    public Page<Warehouse> findByProductCategoryCategoryid(Long categoryid, Pageable pageable);

    public Page<Warehouse> findByProductNameContaining(String searchText, Pageable pageable);

    public Warehouse findBySku(String sku);

    public Warehouse findByProduct(Product product);

    public Warehouse findByProductProductid(Long productid);

    @Query(value = "SELECT warehouseid, name FROM warehouse", nativeQuery = true)
    public List<Warehouse> findAllReduced();
}
