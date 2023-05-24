package fr.poei.fines_saveurs_fo.controller.dto;


import fr.poei.fines_saveurs_fo.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data @NoArgsConstructor
public class CustomerDto {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
}
