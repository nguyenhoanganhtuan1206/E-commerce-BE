package com.ecommerce.domain.brand.mapper;

import com.ecommerce.domain.brand.BrandDTO;
import com.ecommerce.persistent.brand.BrandEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;

@UtilityClass
public class BrandDTOMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static BrandDTO toBrandDTO(final BrandEntity brandEntity) {
        return modelMapper.map(brandEntity, BrandDTO.class);
    }

    public static List<BrandDTO> toBrandDTOs(final List<BrandEntity> brandEntities) {
        return brandEntities.stream()
                .map(BrandDTOMapper::toBrandDTO)
                .toList();
    }
}
