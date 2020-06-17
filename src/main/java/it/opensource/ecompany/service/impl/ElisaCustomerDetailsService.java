package it.opensource.ecompany.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;

// import it.opensource.ecompany.service.CustomersService;

public class ElisaCustomerDetailsService {

    //private final CustomersService customersService;

//    @Autowired
//    public ElisaCustomerDetailsService(CustomersService customersService) {

//        this.customersService = customersService;
//    }
    /*
     * @Override public UserDetails loadUserByUsername(String username) throws
     * UsernameNotFoundException {
     * 
     * Customer customer = customersService.getCustomerByUsername(username);
     * 
     * if (customer == null) { throw new
     * UsernameNotFoundException("Invalid username/password."); }
     * 
     * return new ElisaCustomerDetails(customer); }
     * 
     * private final class ElisaCustomerDetails extends Customer implements
     * UserDetails {
     * 
     * ElisaCustomerDetails(Customer customer) {
     * 
     * setCustomerid(customer.getCustomerid()); setEmail(customer.getEmail());
     * setFirstname(customer.getFirstname()); setLastname(customer.getLastname());
     * setPassword(customer.getPassword()); setUsername(customer.getUsername()); }
     * 
     * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
     * 
     * return ElisaCustomerAuthorityUtils.createAuthorities(this); }
     * 
     * @Override public String getUsername() {
     * 
     * return getEmail(); // return getUsername(); }
     * 
     * @Override public boolean isAccountNonExpired() {
     * 
     * return true; }
     * 
     * @Override public boolean isAccountNonLocked() {
     * 
     * return true; }
     * 
     * @Override public boolean isCredentialsNonExpired() {
     * 
     * return true; }
     * 
     * @Override public boolean isEnabled() {
     * 
     * return true; }
     * 
     * private static final long serialVersionUID = 3384436451564509032L; }
     */
}
