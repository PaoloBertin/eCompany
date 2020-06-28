package it.opensource.ecompany.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.opensource.ecompany.domain.Movement;
import it.opensource.ecompany.service.MovementsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementsServiceImplTest {

    @Autowired
    private MovementsService movementsService;

    @Test
    public void getAllMovementsTest() {

        List<Movement> movements = movementsService.getAllMovements();

        assertThat(movements.size()).isEqualTo(10);
    }

    @Test
    public void getAllMovementsAndLineItemsTest() {

        int expected = 3;
        int actual   = movementsService.getAllMovements().get(0).getLineitems().size();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void getAllMovementsAndLineItemsAndProductTest() {

        String expected = "Da Visual Basic a Java";
        String actual   = movementsService.getAllMovements().get(0).getLineitems().get(0).getProduct().getName();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void getMovementByIdTest() {

        double expected = 169.50;
        double actual   = movementsService.getMovementById(1L).getTotalamount();

        assertThat(actual).isEqualTo(expected);
    }
}
