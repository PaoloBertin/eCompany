package it.opensource.ecompany.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "product_prices")
@Entity
public class ProductPrice {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_code")
    private String productCode;

    private BigDecimal price;

    @Version
    private Long version;

    public ProductPrice() {

    }

    public ProductPrice(String productCode, BigDecimal price) {

        this.productCode = productCode;
        this.price = price;
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

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productCode);
    }

    @Override
    public String toString() {

        return "ProductPrice{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", price=" + price +
                ", version=" + version +
                '}';
    }
}
