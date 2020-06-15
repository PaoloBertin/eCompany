package it.opensource.ecompany.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "lineitem")
@Entity
public class Lineitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              lineitemid;

    @OneToOne
    @JoinColumn(name = "productid")
    private Product           product;

    private Double            quantity;

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

}
