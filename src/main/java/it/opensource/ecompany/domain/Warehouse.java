package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "warehouses")
@Entity
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseid;

    private String name;

    @Version
    private Long version;

    public Long getWarehouseid() {

        return warehouseid;
    }

    public void setWarehouseid(Long warehouseid) {

        this.warehouseid = warehouseid;
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
        result = prime * result + ((warehouseid == null) ? 0 : warehouseid.hashCode());
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
        Warehouse other = (Warehouse) obj;
        if (warehouseid == null) {
            if (other.warehouseid != null)
                return false;
        } else if (!warehouseid.equals(other.warehouseid))
            return false;
        return true;
    }

}
