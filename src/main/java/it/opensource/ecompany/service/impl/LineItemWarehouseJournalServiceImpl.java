package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemWarehouseJournal;
import it.opensource.ecompany.repository.LineItemWarehouseJournalRepository;
import it.opensource.ecompany.service.LineItemWarehouseJournalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("LineItemWarehouseJournalService")
public class LineItemWarehouseJournalServiceImpl implements LineItemWarehouseJournalService {

    private final LineItemWarehouseJournalRepository lineItemWarehouseJournalRepository;

    public LineItemWarehouseJournalServiceImpl(LineItemWarehouseJournalRepository lineItemWarehouseJournalRepository) {

        this.lineItemWarehouseJournalRepository = lineItemWarehouseJournalRepository;
    }

    @Override
    public Optional<LineItemWarehouseJournal> getLineItemWarehouseJournalById(Long lineItemWarehouseJournalId) {

        return lineItemWarehouseJournalRepository.findById(lineItemWarehouseJournalId);
    }

}
