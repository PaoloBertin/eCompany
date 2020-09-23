package it.opensource.ecompany.repository;

import it.opensource.ecompany.domain.Ware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaresRepository extends JpaRepository<Ware, Long> {

    Page<Ware> findByWarehouseWarehouseid(Long warehouseId, Pageable pageable);

    List<Ware> findBySkuAndWarehouseWarehouseid(String sku, Long warehouseId);

    Ware findBySku(String sku);

    @Query("SELECT w, p FROM Ware w, Product p WHERE w.sku = p.isbn")
    List<Object[]> findAllWaresWithProduct();

    @Query("SELECT w, p FROM Ware w, Product p WHERE w.sku = p.isbn")
    Page<Object[]> findAllWaresPageable(Pageable pageable);

    @Query("SELECT w, p FROM Ware w, Product p WHERE w.sku = p.isbn AND w.warehouse.warehouseid = :warehouseId")
    Page<Object[]> findAllWaresInWarehousePageable(Long warehouseId, Pageable pageable);

}
