package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "warehouses")
@Entity
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseid;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "warehouses_wares",
        joinColumns = {@JoinColumn(name = "warehouse_id", referencedColumnName = "warehouseid", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "ware_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private List<Ware> wares = new ArrayList<>();

    @Version
    private Long       version;

    public void addWare(Ware ware) {
        wares.add(ware);
        ware.getWarehouses().add(this);
    }

    public void removeWare(Ware ware) {
        wares.remove(ware);
        ware.getWarehouses().remove(this);
    }

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

    public List<Ware> getWares() {

        return wares;
    }

    public void setWares(List<Ware> wares) {

        this.wares = wares;
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
