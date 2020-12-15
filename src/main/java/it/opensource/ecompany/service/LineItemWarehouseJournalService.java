package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemWarehouseJournal;

import java.util.Optional;

public interface LineItemWarehouseJournalService {

    Long getNumberLineItemWarehouseJournal();

    Long getNumberLineItemWarehouseJournalByProduct(Long productId);

    Optional<LineItemWarehouseJournal> getLineItemWarehouseJournalById(Long lineItemWarehouseJournalId);

}
