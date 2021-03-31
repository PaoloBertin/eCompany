package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.WarehouseJournal;
import it.opensource.ecompany.service.WarehouseJournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@RequestMapping("/api/warehouseJournal")
@RestController
public class WarehouseJournalResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseJournalResource.class);

    private final WarehouseJournalService warehouseJournalService;

    public WarehouseJournalResource(WarehouseJournalService warehouseJournalService) {

        this.warehouseJournalService = warehouseJournalService;
    }

    @GetMapping(value = "/all/all", params = "number")
    public Long getWarehouseJournalNumber() {

        return null;
    }

    @GetMapping("/all/{warehousejournalId}")
    public ResponseEntity<WarehouseJournal> getWarehouseJournalById(@PathVariable(name = "warehousejournalId") Long id) {

        log.debug("REST request to get a WarehouseJournal for id");

        WarehouseJournal warehouseJournal = warehouseJournalService.getWarehouseJournalById(id)
                                                                   .get();   // TODO restituire messaggio se oggetto non esiste
        return ResponseEntity.ok()
                             .body(warehouseJournal);

    }

    @GetMapping(path="/{warehouseId}/all")
    public ResponseEntity<Page<WarehouseJournal>> getAllWarehouseJournalByWarehouseId(@PathVariable(name = "warehouseId") Long id,
                                                                                      @PageableDefault Pageable pageable) {

        log.debug("REST request to get a page of WarehouseJournal for warehouse with id =" + id);

        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getByWarehouseId(id, pageable);

        return ResponseEntity.ok()
                             .body(warehouseJournals); // TODO resituire contenuto e/o messaggio
    }

    @GetMapping(path="/all/all", params = "warehouseName")
    public ResponseEntity<Page<WarehouseJournal>> getAllWarehouseJournalByWarehouseName(@RequestParam(name = "warehouseName") String name,
                                                                                        @PageableDefault Pageable pageable) {

        log.debug("REST request to get a page of WarehouseJournal for warehouse with name = " + name);

        Page<WarehouseJournal> warehouseJournals = warehouseJournalService.getByWarehouseName(name, pageable);

        return ResponseEntity.ok()
                             .body(warehouseJournals); // TODO resituire contenuto e/o messaggio
    }

    @GetMapping(path = "/{warehouseId}/all", params = "dateStart, dateEnd")
    public ResponseEntity<Page<WarehouseJournal>> getWarehouseJournmalBetweenDate(@PageableDefault Pageable pageable,
                                                                                  @PathVariable("warehouseJournalId") Long id,
                                                                                  @RequestParam("dateStart") LocalDate dateStart,
                                                                                  @RequestParam("dateEnd") LocalDate dateEnd) {

        log.debug(
            "REST request to get a page of WarehouseJournal for warehouse with id =" + id + " and dates between " + dateStart + " and " + dateEnd);

        Page<WarehouseJournal> warehouseJournalPage = warehouseJournalService.getAllWarehouseJournalByDocumentDateBetween(id,
                                                                                                                          dateStart,
                                                                                                                          dateEnd,
                                                                                                                          pageable);
        return ResponseEntity.ok()
                             .body(warehouseJournalPage); // TODO resituire contenuto e/o messaggio
    }

    @PostMapping
    public ResponseEntity<WarehouseJournal> createWarehouseJournal(@Valid @RequestBody WarehouseJournal warehouseJournal)
        throws URISyntaxException {

        log.debug("REST request to save WarehouseJournal: {}", warehouseJournal);
        if (warehouseJournal.getId() != null) {
            // TODO
        }
        WarehouseJournal savedWarehouseJournal = warehouseJournalService.saveWarehouseJournal(warehouseJournal);

        return ResponseEntity.created(new URI("/api/warehouseJournal/" + savedWarehouseJournal.getId()))
                             .body(savedWarehouseJournal);
    }

    @PutMapping
    public ResponseEntity<WarehouseJournal> updateWarehouseJournal(@Valid @RequestBody WarehouseJournal warehouseJournal)
        throws URISyntaxException {

        log.debug("REST request to update WarehouseJournal: {}", warehouseJournal);

        if (warehouseJournal.getId() == null) {
            // TODO
        }
        WarehouseJournal updatedWarehouseJournal = warehouseJournalService.saveWarehouseJournal(warehouseJournal);

        return ResponseEntity.ok()
                             .body(updatedWarehouseJournal);
    }

    @DeleteMapping("/{warehouseJournalId}")
    public ResponseEntity<Void> deleteWarehouseJournal(@PathVariable Long warehouseJournalId) {

        log.debug("REST request to delete warehouseJournal : {}", warehouseJournalId);

        warehouseJournalService.deleteWarehouseJournal(warehouseJournalId);

        return ResponseEntity.noContent()
                             .build();
    }

}
