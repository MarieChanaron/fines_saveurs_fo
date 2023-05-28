package fr.poei.fines_saveurs_fo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class OrderDto {

    private int id;
    private LocalDateTime createdAt;
    private double amountPaid;
    private CartDto cartDto;
}
