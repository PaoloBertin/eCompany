package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.PriceList;
import it.opensource.ecompany.repository.PriceListsRepository;
import it.opensource.ecompany.service.PriceListsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("priceListsService")
public class PriceListsServiceImpl implements PriceListsService {

    private final Logger log = LoggerFactory.getLogger(PriceListsServiceImpl.class);

    private final PriceListsRepository priceListsRepository;

    public PriceListsServiceImpl(PriceListsRepository priceListsRepository) {

        this.priceListsRepository = priceListsRepository;
    }

    @Override
    public Optional<PriceList> getPriceListById(Long priceListId) {

        return priceListsRepository.findById(priceListId);
    }

    @Override
    public PriceList getPriceListdByProductCode(String productCode) {

        PriceList priceList = priceListsRepository.findByProductCode(productCode);
        log.debug(priceList.toString());

        return priceList;
    }

}
