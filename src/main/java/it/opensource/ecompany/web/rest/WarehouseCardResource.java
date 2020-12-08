package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.WarehouseCardService;
import it.opensource.ecompany.web.rest.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/warehouseCard")
@RestController
public class WarehouseCardResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseCardResource.class);

    private final WarehouseCardService warehouseCardService;

    public WarehouseCardResource(WarehouseCardService warehouseCardService) {

        this.warehouseCardService = warehouseCardService;
    }

    @GetMapping(params = "numberWarehouseCard")
    public ResponseEntity<ResponseObject> getNumberWarehouseCard() {

        ResponseObject responseObject = new ResponseObject(warehouseCardService.getNumberWarehouseCards());

        return ResponseEntity.ok()
                             .body(responseObject);
    }

    @GetMapping(path = "/{warehouseId}", params = "numberWarehouseCardByWarehouse")
    public ResponseEntity<ResponseObject> getNumberWarehouseCardsBydWarehouseId(@PathVariable("warehouseId") Long warehouseId,
                                                                                @RequestParam("numberWarehouseCardByWarehouse") String numberByWarehouse) {

        Long numberWarehouseCards = warehouseCardService.getNumberWarehouseCardsBydWarehouseId(warehouseId);
        ResponseObject responseObject = new ResponseObject(numberWarehouseCards);

        return ResponseEntity.ok()
                             .body(responseObject);
    }

    @GetMapping(path = "/{warehouseId}/{productId}", params = "numberByWarehouseAndProductId")
    public ResponseEntity<ResponseObject> getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(
        @PathVariable("warehouseId") Long warehouseId, @PathVariable("productId") Long productId,
        @RequestParam("numberByWarehouseAndProductId") String numberByWarehouseAndProductId) {

        Long numberWarehouseCards = warehouseCardService.getNumberWarehouseCardsByWarehouseIdAndLineItemProductId(warehouseId, productId);
        ResponseObject responseObject = new ResponseObject(numberWarehouseCards);

        return ResponseEntity.ok()
                             .body(responseObject);
    }

    @GetMapping(path = "/all/{warehouseCardId}")
    public ResponseEntity getWarehouseCardById(@PathVariable("warehouseCardId") Long id) {

        log.debug("REST request to get warehouseCard with id =" + id);

        Optional<WarehouseCard> warehouseCard = warehouseCardService.getWarehouseCardById(id);
        log.debug("warehouseJournal with id =" + warehouseCard.toString());

        return warehouseCard.map(response -> ResponseEntity.ok()
                                                           .body(response))
                            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/{warehouseId}/all")
    public ResponseEntity<Page<WarehouseCard>> getWarehouseCardByWarehouse(@PathVariable("warehouseId") Long id,
                                                                           @PageableDefault Pageable pageable) {

        log.debug("REST request to get warehouseCard of the warehouse with id = " + id);

        Page<WarehouseCard> warehouseCards = warehouseCardService.getWarehouseCardsByWarehouseByPage(id, pageable);

        return ResponseEntity.ok()
                             .body(warehouseCards);
    }

    @GetMapping(path = "/{warehouseId}/all/{productId}")
    public ResponseEntity<Page<WarehouseCard>> getWarehouseCardByWarehouseAndProduct(@PathVariable("warehouseId") Long warehouseId,
                                                                                     @PathVariable("productId") Long productId,
                                                                                     @PageableDefault Pageable pageable) {

        log.debug("REST request to get warehouseCard of the warehouse with id=" + warehouseId + " and productId=" + productId);
        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndLineItemProductByPage(warehouseId, productId,
                                                                                                           pageable);

        return ResponseEntity.ok()
                             .body(warehouseCards);
    }

}
