package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItemWarehouseCard;

import java.util.Optional;

public interface LineItemWarehouseCardService {

    Optional<LineItemWarehouseCard> getLineItemWarehouseCardById(Long lineItemWarehouseCardId);

}
