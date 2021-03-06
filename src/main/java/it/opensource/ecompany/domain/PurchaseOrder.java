package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private State state;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;

    @JoinTable(name = "purchase_orders_line_items",
        joinColumns = @JoinColumn(name = "purchase_orders_id"),
        inverseJoinColumns = @JoinColumn(name = "line_items_purchase_orders_id"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<LineItemPurchaseOrder> lineItemPurchaseOrders = new ArrayList<>();

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Date getDatePurchase() {

        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {

        this.datePurchase = datePurchase;
    }

    public BigDecimal getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {

        this.totalAmount = totalAmount;
    }

    public State getState() {

        return state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public List<LineItemPurchaseOrder> getLineItemPurchaseOrders() {

        return lineItemPurchaseOrders;
    }

    public void setLineitems(List<LineItemPurchaseOrder> lineItemPurchaseOrders) {

        this.lineItemPurchaseOrders = lineItemPurchaseOrders;
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
