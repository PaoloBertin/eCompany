package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Category;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductForm {

    private Long id;

    private Long categoryId;

    private Long warehouseId;

    private String name;

    private String description;

    @NotNull
    private String isbn;

    private BigDecimal price;

    private String categoryProduct;

    private Category category;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(Long categoryId) {

        this.categoryId = categoryId;
    }

    public Long getWarehouseId() {

        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {

        this.warehouseId = warehouseId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getIsbn() {

        return isbn;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public String getCategoryProduct() {

        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {

        this.categoryProduct = categoryProduct;
    }

    /*
        public byte[] getImage() {

            return image;
        }

        public void setImage(byte[] image) {

            this.image = image;
        }
    */
    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

}
