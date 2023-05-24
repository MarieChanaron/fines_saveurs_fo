package fr.poei.fines_saveurs_fo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CartDto {

    private long id;
    private CustomerDto customerDto;
    private LocalDateTime createdAt;
}
