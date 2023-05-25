package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @Data
public class AddressCustomerId implements Serializable {

    private Customer customer;
    private Address address;
}
