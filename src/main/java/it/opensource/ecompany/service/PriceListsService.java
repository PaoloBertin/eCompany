package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.PriceList;

import java.util.Optional;

public interface PriceListsService {

    Optional<PriceList> getPriceListById(Long priceListId);

    PriceList getPriceListdByProductCode(String productCode);

}
