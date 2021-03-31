package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.UserContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserContextImpl implements UserContext {

    private final CustomersService customersService;

    private final UserDetailsService userDetailsService;

    public UserContextImpl(CustomersService customersService, UserDetailsService userDetailsService) {

        this.customersService = customersService;
        this.userDetailsService = userDetailsService;
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCurrentCustomer() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        String principal = authentication.getName();
        if (authentication == null || principal.equals("anonymousUser")) {
            return null;
        }

        User user = (User) authentication.getPrincipal();

        String username = user.getUsername();

        if (username == null) {
            return null;
        }
        Customer customer = customersService.getCustomerByUsername(username);
        if (customer == null) {
            throw new IllegalStateException("Spring Security is not in synch with Customer. Could not find user with username " + username);
        }

        return customer;
    }

    @Override
    public void setCurrentCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getEmail());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                     customer.getPassword(),
                                                                                                     userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
