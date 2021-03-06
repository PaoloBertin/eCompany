package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.WarehouseCard;
import it.opensource.ecompany.service.WarehouseCardService;
import it.opensource.ecompany.web.rest.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Profile("rest")
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

    @GetMapping(path = "/{warehouseId}/{productCode}", params = "numberByWarehouseAndProductCode")
    public ResponseEntity<ResponseObject> getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(
        @PathVariable("warehouseId") Long warehouseId, @PathVariable("productCode") String productCode,
        @RequestParam("numberByWarehouseAndProductCode") String numberByWarehouseAndProductCode) {

        Long numberWarehouseCards = warehouseCardService.getNumberWarehouseCardsByWarehouseIdAndLineItemProductCode(warehouseId,
                                                                                                                    productCode);
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
    public ResponseEntity<Page<WarehouseCard>> getWarehouseCardByWarehouse(@PathVariable("warehouseId") Long warehouseId,
                                                                           @PageableDefault Pageable pageable) {

        log.debug("REST request to get warehouseCard of the warehouse with id = " + warehouseId);

        Page<WarehouseCard> warehouseCards = warehouseCardService.getAllWarehouseCardsByWarehouseIdByPage(warehouseId, pageable);

        return ResponseEntity.ok()
                             .body(warehouseCards);
    }

    @GetMapping(path = "/{warehouseId}/all/{productCode}")
    public ResponseEntity<Page<WarehouseCard>> getWarehouseCardByWarehouseAndProduct(@PathVariable("warehouseId") Long warehouseId,
                                                                                     @PathVariable("productCode") String productCode,
                                                                                     @PageableDefault Pageable pageable) {

        log.debug("REST request to get warehouseCard of the warehouse with code=" + warehouseId + " and productCode=" + productCode);

        Page<WarehouseCard> warehouseCards = warehouseCardService.getByWarehouseIdAndProductCodeByPage(warehouseId, productCode, pageable);

        return ResponseEntity.ok()
                             .body(warehouseCards);
    }

}
