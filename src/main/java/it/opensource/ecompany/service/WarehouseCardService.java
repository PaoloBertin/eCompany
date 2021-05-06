package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.WarehouseCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WarehouseCardService {

    Long getNumberWarehouseCards();

    Long getNumberWarehouseCardsBydWarehouseId(Long wareouseId);

    Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(Long warehouseId, String productCode);

    List<WarehouseCard> getAllWarehouseCards();

    Page<WarehouseCard> getAllWarehouseCardsByPage(Pageable pageable);

    Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId);

    Page<WarehouseCard> getAllWarehouseCardsByWarehouseIdByPage(Long warehouseId, Pageable pageable);

    Page<WarehouseCard> getAllWarehouseCardsByWarehouseIdAndProductCode(Long warehouseId, String productCode, Pageable pageable);

    Page<WarehouseCard> getByWarehouseIdAndProductCodeByPage(Long warehouseId, String productCode, Pageable pageable);

    Page<WarehouseCard> getByWarehouseIdAndProductIsbn(Long warehouseId, String isbn, Pageable pageable);

}
