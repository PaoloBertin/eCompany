package it.opensource.ecompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Movement;
import it.opensource.ecompany.repository.MovementsRepository;
import it.opensource.ecompany.service.MovementsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Paolo Bertin
 *
 */
@Transactional
@Service("movementsService")
public class MovementsServiceImpl implements MovementsService {

    @Autowired
    private MovementsRepository movementsRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Movement> getAllMovements() {

        return movementsRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Movement getMovementById(Long id) {

        return movementsRepository.findById(id).orElse(new Movement());
    }

    @Transactional(readOnly=true)
    @Override
    public List<Movement> getMovementByCustomer(Long customerid) {

        return movementsRepository.findByCustomerCustomerid(customerid);
    }

    @Transactional(readOnly=true)
    @Override
    public void saveMovements(Movement movement) {

        movementsRepository.save(movement);
    }
}
