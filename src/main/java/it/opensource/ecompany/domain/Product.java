package it.opensource.ecompany.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "products")
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    private String isbn;

    private String name;

    private String subtitle;

    private String description;

    private Float price;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid", foreignKey = @ForeignKey(name = "category_id_fk"))
    private Category category;

    @Version
    private Integer version;

    public Long getProductid() {

        return productid;
    }

    public void setProductid(Long productid) {

        this.productid = productid;
    }

    public String getIsbn() {

        return isbn;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
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

    public byte[] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
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
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((productid == null) ? 0 : productid.hashCode());
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
        Product other = (Product) obj;
        if (productid == null) {
            if (other.productid != null)
                return false;
        } else if (!productid.equals(other.productid))
            return false;
        return true;
    }
}
