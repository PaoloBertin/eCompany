package it.opensource.ecompany.configuration;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.impl.util.CustomerUserAuthorityUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

//@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    private final CustomersService customersService;

    public CustomerAuthenticationProvider(CustomersService customersService) {

        this.customersService = customersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        Customer customer = null;
        if (username != null) {
            customer = customersService.getCustomerByUsername(username);
        }
        if (customer == null) {
            throw new UsernameNotFoundException("Invalid username / password");
        }
        String password = customer.getPassword();
        if (!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username / password");
        }
        Collection<? extends GrantedAuthority> authorities = CustomerUserAuthorityUtils.createAuthorities(customer);

        return new UsernamePasswordAuthenticationToken(customer, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
