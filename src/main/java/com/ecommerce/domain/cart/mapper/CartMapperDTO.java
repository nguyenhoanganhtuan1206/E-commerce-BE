package com.ecommerce.domain.cart.mapper;


import com.ecommerce.domain.cart.dto.CartResponseDTO;
import com.ecommerce.persistent.cart.CartEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;

@UtilityClass
public class CartMapperDTO {

    private final ModelMapper modelMapper = new ModelMapper();

    public static CartResponseDTO toCartResponseDTO(final CartEntity cart) {
        final CartResponseDTO cartResponseDTO = modelMapper.map(cart, CartResponseDTO.class);

        if (cart.getProduct() != null) {
            cartResponseDTO.setProductId(cart.getProduct().getId());
        }

        return cartResponseDTO;
    }

    public static List<CartResponseDTO> toCartResponseDTOs(final List<CartEntity> carts) {
        return carts.stream()
                .map(CartMapperDTO::toCartResponseDTO)
                .toList();
    }
}