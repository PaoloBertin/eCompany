package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.LineItem;

import java.util.Optional;

public interface LineItemService {

    Optional<LineItem> getLineItemById(Long lineItemId);

}
