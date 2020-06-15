package it.opensource.ecompany.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    private String            name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<Customer>     customers;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Set<Customer> getCustomers() {

        return customers;
    }

    public void setCustomers(Set<Customer> customers) {

        this.customers = customers;
    }

    @Override
    public String toString() {

        return "Role [id=" + id + ", name=" + name + ", customers=" + customers + "]";
    }

}
