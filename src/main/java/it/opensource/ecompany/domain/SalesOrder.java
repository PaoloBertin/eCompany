package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "sales_orders")
@Entity
public class SalesOrder {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_sale")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSales;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private State state;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JoinTable(name = "sales_orders_line_items",
        joinColumns = @JoinColumn(name = "sales_orders_id"),
        inverseJoinColumns = @JoinColumn(name = "line_items_sales_order_id"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<LineItemSalesOrder> lineItemSalesOrders = new ArrayList<>();

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Date getDateSales() {

        return dateSales;
    }

    public void setDateSales(Date dateSales) {

        this.dateSales = dateSales;
    }

    public Double getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {

        this.totalAmount = totalAmount;
    }

    public State getState() {

        return state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public Supplier getSupplier() {

        return supplier;
    }

    public void setSupplier (Supplier supplier) {

        this.supplier = supplier;
    }

    public List<LineItemSalesOrder> getLineItemSalesOrders() {

        return lineItemSalesOrders;
    }

    public void setLineItemSalesOrders(List<LineItemSalesOrder> lineItemSalesOrders) {

        this.lineItemSalesOrders = lineItemSalesOrders;
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
        SalesOrder other = (SalesOrder) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
