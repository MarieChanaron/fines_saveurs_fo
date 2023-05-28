package fr.poei.fines_saveurs_fo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "text_input")
    private String text_input;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
