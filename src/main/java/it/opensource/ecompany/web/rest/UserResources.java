package it.opensource.ecompany.web.rest;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@RequestMapping("/api/users")
@RestController
public class UserResources {

    private static final Logger log = LoggerFactory.getLogger(UserResources.class);

    private final UserContext userContext;

    public UserResources(UserContext userContext) {

        this.userContext = userContext;
    }

    @GetMapping
    public ResponseEntity<Customer> getCurrentUser() {

        ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(userContext.getCurrentCustomer(), HttpStatus.OK);

        log.trace("restituisce utente corrente");

        return responseEntity;
    }

}