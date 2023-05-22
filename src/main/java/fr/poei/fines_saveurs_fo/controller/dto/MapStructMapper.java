package fr.poei.fines_saveurs_fo.controller.dto;

import fr.poei.fines_saveurs_fo.entity.CartProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    CartProductDto toDto(CartProduct cartProduct);
}
