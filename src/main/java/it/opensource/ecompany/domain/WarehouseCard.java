package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "warehouse_cards")
@Entity
public class WarehouseCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "warehouse_cards_fk_01"))
    @ManyToOne
    private Warehouse warehouse;

    @JoinColumn(name = "line_item_id", foreignKey = @ForeignKey(name = "warehouse_cards_fk_02"))
    @OneToOne
    private LineItem lineItem;

    @JoinColumn(name = "warehouse_card_product_id", foreignKey = @ForeignKey(name = "warehouse_cards_fk_03"))
    @ManyToOne
    private WarehouseCardProduct warehouseCardProduct;

    private Integer stock;

    @Column(name = "inventory_value")
    private BigDecimal inventoryValue;

    @Version
    private Integer version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Warehouse getWarehouse() {

        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public WarehouseCardProduct getWarehouseCardProduct() {

        return warehouseCardProduct;
    }

    public void setWarehouseCardProduct(WarehouseCardProduct warehouseCardProduct) {

        this.warehouseCardProduct = warehouseCardProduct;
    }

    public LineItem getLineItem() {

        return lineItem;
    }

    public void setLineitem(LineItem lineitem) {

        this.lineItem = lineitem;
    }

    public Integer getStock() {

        return stock;
    }

    public void setStock(Integer stock) {

        this.stock = stock;
    }

    public BigDecimal getInventoryValue() {

        return inventoryValue;
    }

    public void setInventoryValue(BigDecimal inventoryValue) {

        this.inventoryValue = inventoryValue;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    @Override
    public String toString() {

        return "WarehouseCard{" + "id=" + id + ", stock=" + stock + ", inventoryValue=" + inventoryValue + ", version=" + version + '}';
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
        WarehouseCard other = (WarehouseCard) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
