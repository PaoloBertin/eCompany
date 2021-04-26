package it.opensource.ecompany.domain.builder;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.domain.ImageProduct;
import it.opensource.ecompany.domain.Product;

import java.math.BigDecimal;

public final class ProductBuilder {

    public static ProductBuilder newBuilder() {

        return new ProductBuilder();
    }

    private Long id;

    private String productCode;

    private String name;

    private String subtitle;

    private String description;

    private BigDecimal price;

    private ImageProduct imageProduct;

    private Category category;

    public ProductBuilder setId(Long id) {

        this.id = id;
        return this;
    }

    public ProductBuilder setProductCode(String productCode) {

        this.productCode = productCode;
        return this;
    }

    public ProductBuilder setName(String name) {

        this.name = name;
        return this;
    }

    public ProductBuilder setSubTitle(String subTitle) {

        this.subtitle = subTitle;
        return this;
    }

    public ProductBuilder setDescription(String description) {

        this.description = description;
        return this;
    }

    public ProductBuilder setPrice(BigDecimal price) {

        this.price = price;
        return this;
    }

    public ProductBuilder setImageProduct() {

        this.imageProduct = imageProduct;
        return this;
    }

    public ProductBuilder setCategory(Category category) {

        this.category = category;
        return this;
    }

    public Product build() {

        return new Product(id, productCode, name, description, price, imageProduct, category);
    }
}
