package it.opensource.ecompany.web.dto;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.ImageProduct;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    private String productCode;

    private String name;

    private String subtitle;

    private String description;

    private Category category;

    private BigDecimal price;

    private ImageProduct imageProduct;

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

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public ImageProduct getImageProduct() {

        return imageProduct;
    }

    public void setImageProduct(ImageProduct imageProduct) {

        this.imageProduct = imageProduct;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }
}
