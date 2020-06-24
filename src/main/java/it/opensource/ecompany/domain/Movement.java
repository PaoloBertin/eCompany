package it.opensource.ecompany.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "movement")
@Entity
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementid; // TODO usare id come nome variabile

    @Temporal(TemporalType.TIMESTAMP)
    private Date datemovement; // TODO usare dateMovement come nome variabile e date_movement come nome colonna

    private Double totalamount; // TODO usare totalAmount come nome variabile

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private State state;

    @OneToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Lineitem> lineitems = new ArrayList<>(); // TODO usare lineItems come nome variabile

    @Version
    private Long version;

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((movementid == null) ? 0 : movementid.hashCode());
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
        Movement other = (Movement) obj;
        if (movementid == null) {
            if (other.movementid != null)
                return false;
        } else if (!movementid.equals(other.movementid))
            return false;
        return true;
    }

}

