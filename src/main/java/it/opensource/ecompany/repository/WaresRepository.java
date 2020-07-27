package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaresRepository extends JpaRepository<Ware, Long> {

    public Long countByWarehouseWarehouseid(Long warehouseid);

    public Page<Ware> findByWarehouseWarehouseid(Long warehouseid, Pageable pageable);

    public Page<Ware> findByWarehouseWarehouseidAndProductCategoryCategoryid(Long warehouseid, Long categoryid,
                                                                             Pageable pageable);

    public Page<Ware> findByWarehouseWarehouseidAndProductNameContaining(Long warehouseid, String searchText,
                                                                         Pageable pageable);

    public Ware findBySku(String sku);
}
