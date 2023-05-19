package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
public class AddressCustomerId implements Serializable {

    @OneToOne
    @JoinColumn(name = "customer", nullable = false, referencedColumnName = "id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "address", nullable = false, referencedColumnName = "id")
    private Address address;
}
