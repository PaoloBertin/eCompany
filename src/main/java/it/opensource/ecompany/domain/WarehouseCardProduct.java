package it.opensource.ecompany.domain;

import it.opensource.ecompany.domain.util.Unit;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "warehouse_card_products")
@Entity
public class WarehouseCardProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "sku")
    private String sku;

    @Column(name = "reorder_quantity")
    private Integer reorderQuantity;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private Unit unit;

    @Column(name = "container")
    private String container;

    @Column(name = "location")
    private String location;

    @Version
    private Integer version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getProductCode() {

        return productCode;
    }

    public void setProductCode(String productCode) {

        this.productCode = productCode;
    }

    public String getSku() {

        return sku;
    }

    public void setSku(String sku) {

        this.sku = sku;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    public Integer getReorderQuantity() {

        return reorderQuantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {

        this.reorderQuantity = reorderQuantity;
    }

    public Unit getUnit() {

        return unit;
    }

    public void setUnit(Unit unit) {

        this.unit = unit;
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


}
