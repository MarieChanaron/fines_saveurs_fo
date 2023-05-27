package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_customer")
@Data @NoArgsConstructor
@IdClass(AddressCustomerId.class)
public class AddressCustomer {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    private String type;
}
