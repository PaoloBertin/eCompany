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

    List<WarehouseCard> getWarehouseCards();

    Optional<WarehouseCard> getWarehouseCardById(Long warehouseCardId);

    Page<WarehouseCard> getAllWarehouseCardsByPage(Pageable pageable);

    Page<WarehouseCard> getAllWarehouseCardsInWarehouseByPage(Long warehouseId, Pageable pageable);

    List<WarehouseCard> getByWarehouseIdAndWarehouseCardProductId(Long warehouseId, Long warehouseCardProductId);

    Page<WarehouseCard> getByWarehouseIdAndLineItemProductByPage(Long warehouseId, Long productId, Pageable pageable);

    Page<WareDto> getAllWaresWithProductPageable(Pageable pageable);

    Page<WareDto> getAllWaresInWarehousePageable(Long warehouseId, Pageable pageable);

}
