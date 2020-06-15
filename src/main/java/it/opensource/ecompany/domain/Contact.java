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

}
