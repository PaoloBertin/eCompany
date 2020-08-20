package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "accounts")
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }
}
