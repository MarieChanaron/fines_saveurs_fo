package fr.poei.fines_saveurs_fo.controller.dto;


import fr.poei.fines_saveurs_fo.entity.Order;
import lombok.Data;


import java.util.List;

@Data
public class CustomerDto {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private List<Order> orders;

}
