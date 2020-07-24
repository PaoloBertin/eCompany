package it.opensource.ecompany.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "categories")
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryid;

    @NotNull
    private String name;

    @Version
    private Long version;

    public Category() {

    }

    public Category(String name) {

        this.name = name;
    }

    public Category(Long id, String name) {

        this.categoryid = id;
        this.name = name;
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
        result = prime * result + ((categoryid == null) ? 0 : categoryid.hashCode());
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
        Category other = (Category) obj;
        if (categoryid == null) {
            if (other.categoryid != null)
                return false;
        } else if (!categoryid.equals(other.categoryid))
            return false;
        return true;
    }

}
