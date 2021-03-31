package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "price_lists")
@Entity
public class PriceList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_list_name")
    private String priceListName;

    @Column(name = "product_code")
    private String productCode;

    private BigDecimal price;

    @Version
    private Long version;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getPriceListName() {

        return priceListName;
    }

    public void setPriceListName(String priceListName) {

        this.priceListName = priceListName;
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
    public String toString() {

        return "PriceList{" +
                "id=" + id +
                ", priceListName='" + priceListName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", price=" + price +
                ", version=" + version +
                '}';
    }
}
