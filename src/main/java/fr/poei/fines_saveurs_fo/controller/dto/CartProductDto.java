package fr.poei.fines_saveurs_fo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CartProductDto {

    private ProductDto productDto;
    private byte quantity;

}
