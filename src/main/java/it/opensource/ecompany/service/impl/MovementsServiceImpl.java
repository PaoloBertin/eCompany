package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Movement;
import it.opensource.ecompany.repository.MovementsRepository;
import it.opensource.ecompany.service.MovementsService;

/**
 * 
 * @author Paolo Bertin
 *
 */
@Service("movementsService")
public class MovementsServiceImpl implements MovementsService {

    @Autowired
    private MovementsRepository movementsRepository;

    @Override
    public List<Movement> getAllMovements() {

        return movementsRepository.findAll();
    }

    public Movement getMovementById(Long id) {

        return movementsRepository.findById(id).orElse(new Movement());
    }

    @Override
    public List<Movement> getMovementByCustomer(Long customerid) {

        return movementsRepository.findByCustomerCustomerid(customerid);
    }

    @Override
    public void saveMovements() {

    }


}
