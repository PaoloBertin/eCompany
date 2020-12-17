package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "line_items_warehouse")
@Entity
public class LineItemWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    private Double quantity;

    private BigDecimal price;

    @Version
    private Long version;

    public LineItemWarehouse() {

    }

    public LineItemWarehouse(String productCode, Double quantity) {

        this.productCode = productCode;
        this.quantity = quantity;
    }

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

    public Double getQuantity() {

        return quantity;
    }

    public void setQuantity(Double quantity) {

        this.quantity = quantity;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
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
        LineItemWarehouse other = (LineItemWarehouse) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
