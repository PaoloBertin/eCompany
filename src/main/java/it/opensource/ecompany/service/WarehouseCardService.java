package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.dto.WareDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WarehouseCardService {

    Long getNumberWarehouseCards();

    Long getNumberWarehouseCardsBydWarehouseId(Long wareouseId);

    Long getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(Long warehouseId, Long productId);

    List<WarehouseCard> getAllWarehouseCards();

    Page<WarehouseCard> getAllWarehouseCardsByPage(Pageable pageable);

    Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId);

    Page<WarehouseCard> getWarehouseCardsByWarehouseByPage(Long warehouseId, Pageable pageable);

    List<WarehouseCard> getWarehouseCardsByWarehouseIdAndProductId(Long warehouseId, Long productId);

    Page<WarehouseCard> getByWarehouseIdAndLineItemProductByPage(Long warehouseId, Long productId, Pageable pageable);

    Page<WareDto> getAllWaresWithProductPageable(Pageable pageable);

    Page<WareDto> getAllWaresInWarehousePageable(Long warehouseId, Pageable pageable);

}
