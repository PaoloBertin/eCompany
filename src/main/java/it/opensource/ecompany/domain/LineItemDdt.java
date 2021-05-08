package it.opensource.ecompany.domain;

import it.opensource.ecompany.domain.util.UnitMeasure;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "line_item_ddts")
@Entity
public class LineItemDdt implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    private BigDecimal price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_measure")
    private UnitMeasure unitMeasure;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public String getProductCode() {

        return productCode;
    }

    public void setProductCode(String productCode) {

        this.productCode = productCode;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public UnitMeasure getUnitMeasure() {

        return unitMeasure;
    }

    public void setUnitMeasure(UnitMeasure unitMeasure) {

        this.unitMeasure = unitMeasure;
    }
}
