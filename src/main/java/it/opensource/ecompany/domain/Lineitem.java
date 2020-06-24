package it.opensource.ecompany.domain;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "lineitem")
@Entity
public class Lineitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineitemid;

    @OneToOne
    @JoinColumn(name = "productid")
    private Product product;

    private Double quantity;

    @Version
    private Long version;

    public Long getLineitemid() {

        return lineitemid;
    }

    public void setLineitemid(Long lineitemid) {

        this.lineitemid = lineitemid;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
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

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((lineitemid == null) ? 0 : lineitemid.hashCode());
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
        Lineitem other = (Lineitem) obj;
        if (lineitemid == null) {
            if (other.lineitemid != null)
                return false;
        } else if (!lineitemid.equals(other.lineitemid))
            return false;
        return true;
    }
}
