package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemWarehouseJournal;

import java.util.Optional;

public interface LineItemWarehouseJournalService {

    Optional<LineItemWarehouseJournal> getLineItemWarehouseJournalById(Long lineItemWarehouseJournalId);

}
