package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Data @NoArgsConstructor
public class Cart implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDateTime createdAt;

}
