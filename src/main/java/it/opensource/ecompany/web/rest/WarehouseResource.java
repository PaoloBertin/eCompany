package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.service.WarehouseService;
import it.opensource.ecompany.web.controller.WarehouseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Profile("rest")
@RequestMapping("/api/warehouses")
@RestController
public class WarehouseResource {

    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService warehouseService;

    public WarehouseResource(final WarehouseService warehouseService) {

        this.warehouseService = warehouseService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouseByPage(@PageableDefault Pageable pageable) {

        log.debug("visualizza elenco magazzini");

        Page<Warehouse> page = warehouseService.getAllWarehousesByPage(pageable);
        return ResponseEntity.ok()
                             .body(page.getContent());

    }

    @GetMapping(value = "/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long warehouseId) {

        log.debug("recupera warehouse per id");
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(warehouseId);

        return warehouse.map(response -> ResponseEntity.ok()
                                                       .body(response))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<Warehouse> getWarehouseByName(@RequestParam String name) {

        log.debug("recupera warehouse con nome " + name);

        Optional<Warehouse> warehouse = warehouseService.getWarehouseByName(name);

        return warehouse.map(response -> ResponseEntity.ok()
                                                       .body(response))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) throws URISyntaxException {

        log.debug("REST request to save Product : {}", warehouse);
        if (warehouse.getId() != null) {
            // TODO
        }
        Warehouse savedWarehouse = warehouseService.saveWarehouse(warehouse);

        return ResponseEntity.created(new URI("/api/warehouses/" + savedWarehouse.getId()))
                             // .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()
                             .body(savedWarehouse);
    }

    @PutMapping
    public ResponseEntity<Warehouse> updateWarehouse(@Valid @RequestBody Warehouse warehouse) throws URISyntaxException {

        log.debug("REST request to update Product : {}", warehouse);

        if (warehouse.getId() == null) {
            // TODO
        }
        Warehouse updatedWarehouse = warehouseService.saveWarehouse(warehouse);

        return ResponseEntity.ok()
                             // .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, product.getId().toString()))
                             .body(updatedWarehouse);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long warehouseId) {

        log.debug("REST request to delete warehouse : {}", warehouseId);

        warehouseService.deleteWarehouse(warehouseId);

        return ResponseEntity.noContent()
                             // .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                             .build();
    }

}
