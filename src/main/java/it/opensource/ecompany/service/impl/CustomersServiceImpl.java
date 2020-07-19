package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.domain.Role;
import it.opensource.ecompany.repository.CustomersRepository;
import it.opensource.ecompany.repository.RolesRepository;
import it.opensource.ecompany.service.CustomersService;
import it.opensource.ecompany.service.impl.util.CustomerUserAuthorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("customersService")
public class CustomersServiceImpl implements CustomersService, UserDetailsService {

    @Autowired
    private CustomersRepository customerRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerById(Long customerId) {

        return customerRepository.findById(customerId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerByEmail(String email) {

        return customerRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerByUsername(String username) {

        return customerRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerByUsernameAndPassword(String username, String password) {

        Customer customer = customerRepository.findByUsernameAndPassword(username, password);

        return customer;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean verifyCustomer(String username, String password) {

        boolean verify = false;

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
        roles.add(rolesRepository.findById(0L).get());

        customer.setRoles(roles);
        Customer result = customerRepository.save(customer);
        customerRepository.flush();

        return result.getCustomerid();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        Collection<? extends GrantedAuthority> authorities = CustomerUserAuthorityUtils.createAuthorities(customer);

        return new CustomerDetails(customer);
    }

    private final class CustomerDetails extends Customer implements UserDetails {

        private static final long serialVersionUID = 1L;

        CustomerDetails(Customer customer) {

            setCustomerid(customer.getCustomerid());
            setEmail(customer.getEmail());
            setFirstname(customer.getFirstname());
            setLastname(customer.getLastname());
            setPassword(customer.getPassword());
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {

            return CustomerUserAuthorityUtils.createAuthorities(this);
        }

        public String getUsername() {

            return getEmail();
        }

        public boolean isAccountNonExpired() {

            return true;
        }

        public boolean isAccountNonLocked() {

            return true;
        }

        public boolean isCredentialsNonExpired() {

            return true;
        }

        public boolean isEnabled() {

            return true;
        }
    }
}
