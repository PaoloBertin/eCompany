package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "wares")
@Entity
public class Ware implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private Float cost;

    private String unit;

    private Integer quantity;

    @Column(name = "reorder_quantity")
    private Integer reorderQuantity;

    @Column(name = "inventory_value")
    private Float inventoryValue;

    private Boolean reorder;

    private String container;

    private String location;

    @JoinColumn(name = "warehouse_id")
    @ManyToOne
    private Warehouse warehouse;

    @Version
    private Integer version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getSku() {

        return sku;
    }

    public void setSku(String sku) {

        this.sku = sku;
    }

    public Float getCost() {

        return cost;
    }

    public void setCost(Float cost) {

        this.cost = cost;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public Integer getReorderQuantity() {

        return reorderQuantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {

        this.reorderQuantity = reorderQuantity;
    }

    public Float getInventoryValue() {

        return inventoryValue;
    }

    public void setInventoryValue(Float inventoryValue) {

        this.inventoryValue = inventoryValue;
    }

    public Boolean getReorder() {

        return reorder;
    }

    public void setReorder(Boolean reorder) {

        this.reorder = reorder;
    }

    public String getContainer() {

        return container;
    }

    public void setContainer(String container) {

        this.container = container;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public Integer getVersion() {

        return version;
    }

    public Warehouse getWarehouse() {

        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public void setVersion(Integer version) {

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
        Ware other = (Ware) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
