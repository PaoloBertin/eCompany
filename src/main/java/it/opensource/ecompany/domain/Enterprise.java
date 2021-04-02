package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "enterprises")
@Entity
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "price_list")
    private String priceList;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    public String getPriceList() {

        return priceList;
    }

    public void setPriceList(String priceList) {

        this.priceList = priceList;
    }
}
