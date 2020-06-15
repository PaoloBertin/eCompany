package it.opensource.ecompany.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "categories")
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              categoryid;

    private String            name;

    public Category() {

    }

    public Category(Long id, String name) {

        this.categoryid = id;
        this.name       = name;
    }

    public Long getCategoryid() {

        return categoryid;
    }

    public void setCategoryid(Long categoryid) {

        this.categoryid = categoryid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

}
