package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WaresRepository extends JpaRepository<Ware, Long> {

    Long countByWarehousesWarehouseid(Long warehouseId);

    Page<Ware> findByWarehousesWarehouseidIn(Collection<Long> warehouseId, Pageable pageable);

    List<Ware> findBySkuAndWarehousesWarehouseidIn(String sku, Collection<Long> warehouseId);

    Ware findByIdAndWarehousesWarehouseid(Long wareId, Long warehouseId);

    Ware findBySku(String sku);
}
