package it.opensource.ecompany.service;

import java.util.List;

import it.opensource.ecompany.domain.Movement;

public interface MovementsService {

    public List<Movement> getAllMovements();

    public Movement getMovementById(Long id);

    public List<Movement> getMovementByCustomer(Long customerid);

    public void saveMovement();
}
