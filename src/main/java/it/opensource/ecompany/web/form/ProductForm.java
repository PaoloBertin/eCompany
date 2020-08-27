package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Category;

import javax.validation.constraints.NotNull;

public class ProductForm {

    private Long productId;

    private Long categoryId;

    private String name;

    private String description;

    @NotNull
    private String isbn;

    private Float price;

    private String categoryProduct;

    //    private  byte[] image;

    private Category category;

    public Long getProductId() {

        return productId;
    }

    public void setProductId(Long productId) {

        this.productId = productId;
    }

    public Long getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(Long categoryId) {

        this.categoryId = categoryId;
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

    public Float getPrice() {

        return price;
    }

    public void setPrice(Float price) {

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
