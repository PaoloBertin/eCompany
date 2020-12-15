package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Customer;
import it.opensource.ecompany.repository.CustomersRepository;
import it.opensource.ecompany.service.CustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("customersService")
public class CustomersServiceImpl implements CustomersService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final CustomersRepository customerRepository;

    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public CustomersServiceImpl(CustomersRepository customerRepository, UserDetailsManager userDetailsManager,
                                PasswordEncoder passwordEncoder) {

        this.customerRepository = customerRepository;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                                 .get();
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
    public Long saveCustomer(final Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }

        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        // rende persistente il cliente
        Customer result = customerRepository.save(customer);
        customerRepository.flush();

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        UserDetails userDetails = new User(customer.getEmail(), customer.getPassword(), authorities);
        // rende persistenti le credenziali del cliente
        userDetailsManager.createUser(userDetails);

        return result.getId();

    }
}
