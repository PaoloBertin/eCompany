package it.opensource.ecompany.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Role;
import it.opensource.ecompany.repository.CustomersRepository;
import it.opensource.ecompany.repository.RolesRepository;
import it.opensource.ecompany.service.CustomersService;

@Service("customersService")
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersRepository customerRepository;
    
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {

        return customerRepository.findById(customerId).get();
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer getCustomerByUsername(String username) {

        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer getCustomerByUsernameAndPassword(String username, String password) {

        Customer customer = customerRepository.findByUsernameAndPassword(username, password);

        return customer;
    }

    @Override
    public boolean verifyCustomer(String username, String password) {

        boolean  verify   = false;

        Customer customer = customerRepository.findByUsernameAndPassword(username, password);

        if (customer != null) {
            verify = true;
        }

        return verify;
    }

    @Override
    public Long createCustomer(final Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }

        if (customer.getCustomerid() != null) {
            throw new IllegalArgumentException("customer.getCustomerid() must be null when creating a " + Customer.class.getName());
        }
        
        Set<Role> roles = new HashSet<>();
//        roles.add(rolesRepository.findOne(0));
        roles.add(rolesRepository.findById(0L).get());
        
        customer.setRoles(roles);
        Customer result = customerRepository.save(customer);
        customerRepository.flush();
        
        return result.getCustomerid();
    }

    @Override
    public Customer getCurrentCustomer() {

//        SecurityContext context        = SecurityContextHolder.getContext();
//        Authentication  authentication = context.getAuthentication();

//        if (authentication == null) {
//            return null;
//        }

//        if (authentication.getPrincipal() == "anonymousUser") {
//            return null;
//        }

////        String email = authentication.getName();
//        String username = authentication.getName();

          return null;


//        return customerRepository.findByUsername(username);

//        return (Customer) authentication.getPrincipal();

    }

    @Override
    public void setCurrentCustomer(Customer customer) {

//        if (customer == null) {
//            throw new IllegalArgumentException("user cannot be null");
//        }

//         UserDetails    userDetails    = userDetailsService.loadUserByUsername(customer.getUsername());

//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, customer.getPassword(), userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        Collection<? extends GrantedAuthority> authorities    = ElisaCustomerAuthorityUtils.createAuthorities(customer);
//        UsernamePasswordAuthenticationToken    authentication = new UsernamePasswordAuthenticationToken(customer, customer.getPassword(), authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        Collection     authorities    = ElisaCustomerAuthorityUtils.createAuthorities(customer);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(customer, customer.getPassword(), authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
