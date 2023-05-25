package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "address")
@Data @NoArgsConstructor
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "street")
    private String street;
    @Column(name = "additional_info")
    private String additionalInformation;
    @Column(name = "city")
    private String city;
    @Column(name = "postcode")
    private String postcode;
}