package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_customer")
@Data @NoArgsConstructor
public class AddressCustomer {

    @EmbeddedId
    private AddressCustomerId addressCustomerId;
    private String type;
}
