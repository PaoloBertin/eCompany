package it.opensource.ecompany.domain;

import javax.persistence.*;

@Table(name = "contacts")
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String cellular;

    @Column(name = "landline_phone")
    private String landlinePhone;

    @Version
    private Long Version;

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getCellular() {

        return cellular;
    }

    public void setCellular(String cellular) {

        this.cellular = cellular;
    }

    public String getLandlinePhone() {

        return this.landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {

        this.landlinePhone = landlinePhone;
    }

    public Long getVersion() {

        return Version;
    }

    public void setVersion(Long version) {

        Version = version;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Contact other = (Contact) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
