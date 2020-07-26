package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/users")
@RestController
public class UserResources {

    private final UserContext userContext;

    public UserResources(UserContext userContext) {

        this.userContext = userContext;
    }

    @GetMapping
    public ResponseEntity<Customer> getCurrentUser() {

        ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(userContext.getCurrentCustomer(),
                                                                               HttpStatus.OK);

        log.trace("restituisce utente corrente");

        return responseEntity;
    }
}