package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Data @NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDateTime createdAt;
    @Column(name = "amount_paid")
    private double amountPaid;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "cart")
    private Cart cart;
}