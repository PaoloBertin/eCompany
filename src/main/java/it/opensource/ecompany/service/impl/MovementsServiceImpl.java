package it.opensource.ecompany.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.domain.*;
import it.opensource.ecompany.repository.WarehouseRepository;
import it.opensource.ecompany.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.repository.MovementsRepository;
import it.opensource.ecompany.service.MovementsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Paolo Bertin
 */
@Transactional
@Service("movementsService")
public class MovementsServiceImpl implements MovementsService {

    @Autowired
    private CartBean cartBean;

    @Autowired
    private UserContext userContext;

    @Autowired
    private MovementsRepository movementsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Movement> getAllMovements() {

        return movementsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Movement getMovementById(Long id) {

        return movementsRepository.findById(id).orElse(new Movement());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movement> getMovementByCustomer(Long customerid) {

        return movementsRepository.findByCustomerCustomerid(customerid);
    }

    @Transactional(readOnly = true)
    @Override
    public void saveMovements() {

        Customer customer = userContext.getCurrentCustomer();

        Movement movement = new Movement();
        movement.setDatemovement(new Date());
        movement.setTotalamount(cartBean.getTotalCost());
        movement.setState(State.nuovo);
        movement.setCustomer(customer);

        // costruzione lista LineItem
        List<Lineitem> lineitems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartBean.getProducts().entrySet()) {
            Lineitem lineitem = new Lineitem();
            lineitem.setProduct(entry.getKey());
            lineitem.setQuantity(Double.valueOf(entry.getValue()));
            lineitems.add(lineitem);
        }
        movement.setLineitems(lineitems);
        movementsRepository.save(movement);

        // scala quantità prodotti in magazzino

    }
}
