package it.opensource.ecompany.domain;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "customers")
@Entity
public class Customer implements Principal, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerid;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",
        joinColumns = @JoinColumn(name = "customerid"),
        inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Role> roles;

    private String email;

    @Transient
    private String fiscalcode;

    @Transient
    private String description;

    @JoinColumn(name = "contact_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Version
    private Long version;

    public Set<Role> getRoles() {

        return roles;
    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

    public Long getCustomerid() {

        return customerid;
    }

    public void setCustomerid(Long customerid) {

        this.customerid = customerid;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {

        return email;
    }


    public void setEmail(String email) {

        this.email = email;
    }

    public String getFiscalcode() {

        return fiscalcode;
    }

    public void setFiscalcode(String fiscalcode) {

        this.fiscalcode = fiscalcode;
    }

    public String getDescription() {

        description = firstname + " " + lastname;

        return description;
    }

    public Contact getContact() {

        return contact;
    }

    public void setContact(Contact contact) {

        this.contact = contact;
    }

    public Address getAddress() {

        return address;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    @JsonIgnore
    @Override
    public String getName() {

        return getUsername();
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerid == null) ? 0 : customerid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (customerid == null) {
            if (other.customerid != null)
                return false;
        } else if (!customerid.equals(other.customerid))
            return false;
        return true;
    }
}

