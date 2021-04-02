package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Enterprise;
import it.opensource.ecompany.repository.EnterprisesRepository;
import it.opensource.ecompany.service.EnterprisesService;
import org.springframework.stereotype.Service;

@Service("enterprisesService")
public class EnterprisesServiceImpl implements EnterprisesService {

    private final EnterprisesRepository enterprisesRepository;

    public EnterprisesServiceImpl(EnterprisesRepository enterprisesRepository) {

        this.enterprisesRepository = enterprisesRepository;
    }

    @Override
    public String  getEnterprisePriceList() {

        Enterprise enterprise = enterprisesRepository.findById(1L).get();
        return enterprise.getPriceList();
    }
}
