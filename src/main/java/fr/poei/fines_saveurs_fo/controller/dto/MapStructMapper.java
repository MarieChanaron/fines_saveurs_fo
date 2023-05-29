package fr.poei.fines_saveurs_fo.controller.dto;

import fr.poei.fines_saveurs_fo.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    CartProductDto toDto(CartProduct cartProduct);
    ProductDto toDto(Product product);
    CustomerDto toDto(Customer customer);
    CartDto toDto(Cart cart);
    Cart fromDto(CartDto cart);
    Customer fromDto(CustomerDto customerDto);
    OrderDto toDto(Order order);
}
