package it.opensource.ecompany.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Table(name = "purchase_orders")
@Entity
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_purchase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePurchase;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private State state;

    @OneToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @JoinTable(name = "purchase_orders_lineitems",
        joinColumns = @JoinColumn(name = "purchase_orders_id"),
        inverseJoinColumns = @JoinColumn(name = "lineitems_lineitemid"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Lineitem> lineitems = new ArrayList<>();

    @Version
    private Long version;

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        PurchaseOrder other = (PurchaseOrder) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
