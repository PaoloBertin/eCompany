package it.opensource.ecompany.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "warehouse")
@Entity
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseid;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    @Column(name = "sku")
    private String sku;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reorder_quantity")
    private Integer reorderQuantity;

    @Column(name = "inventory_value")
    private Float inventoryValue;

    @Column(name = "reorder")
    private Boolean reorder;

    @Column(name = "container")
    private String container;

    @Column(name = "location")
    private String location;

    @Version
    private Long version;

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((warehouseid == null) ? 0 : warehouseid.hashCode());
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
        Warehouse other = (Warehouse) obj;
        if (warehouseid == null) {
            if (other.warehouseid != null)
                return false;
        } else if (!warehouseid.equals(other.warehouseid))
            return false;
        return true;
    }

}
