package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItemWarehouseCard;
import it.opensource.ecompany.repository.LineItemWarehouseCardRepository;
import it.opensource.ecompany.service.LineItemWarehouseCardService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lineItemWarehouseCardService")
public class LineItemWarehouseCardServiceImpl implements LineItemWarehouseCardService {

    private final LineItemWarehouseCardRepository lineItemWarehouseCardRepository;

    public LineItemWarehouseCardServiceImpl(LineItemWarehouseCardRepository lineItemWarehouseCardRepository) {

        this.lineItemWarehouseCardRepository = lineItemWarehouseCardRepository;
    }

    @Override
    public Optional<LineItemWarehouseCard> getLineItemWarehouseCardById(Long lineItemWarhouseCardId) {

        return lineItemWarehouseCardRepository.findById(lineItemWarhouseCardId);
    }

}
