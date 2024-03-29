package com.ecommerce.domain.user.mapper;

import com.ecommerce.api.profile.dto.UserUpdateResponseDTO;
import com.ecommerce.persistent.user.UserEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class UserUpdateMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserUpdateResponseDTO toUserUpdateResponseDTO(final UserEntity userEntity) {
        return modelMapper.map(userEntity, UserUpdateResponseDTO.class);
    }
}
