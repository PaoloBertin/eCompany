package it.opensource.ecompany.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "products")
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_code")
    private String productCode;

    private String name;

    private String subtitle;

    private String description;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "products_fk_01"))
    private Category category;

    @OneToOne
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "products_fk_02"))
    private ImageProduct imageProduct;

    @Version
    private Integer version;

    public Product() {

    }

    public Product(String productCode, String name) {

        this.productCode = productCode;
        this.name = name;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSubtitle() {

        return subtitle;
    }

    public void setSubtitle(String subtitle) {

        this.subtitle = subtitle;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Float getPrice() {

        return price;
    }

    public void setPrice(Float price) {

        this.price = price;
    }

    public ImageProduct getImageProduct() {

        return imageProduct;
    }

    public void setImageProduct(ImageProduct imageProduct) {

        this.imageProduct = imageProduct;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return getProductCode().equals(product.getProductCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProductCode());
    }

}
