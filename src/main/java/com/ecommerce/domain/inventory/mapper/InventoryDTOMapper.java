package com.ecommerce.domain.inventory.mapper;

import com.ecommerce.domain.inventory.dto.InventoryCreateRequestDTO;
import com.ecommerce.domain.inventory.dto.InventoryDTO;
import com.ecommerce.domain.inventory.dto.InventoryResponseDTO;
import com.ecommerce.persistent.inventory.InventoryEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;

@UtilityClass
public class InventoryDTOMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static InventoryDTO toInventoryDTO(final InventoryEntity inventory) {
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public static InventoryCreateRequestDTO toInventoryRequestDTO(final InventoryEntity inventory) {
        return modelMapper.map(inventory, InventoryCreateRequestDTO.class);
    }

    public static InventoryEntity toInventoryEntity(final InventoryCreateRequestDTO inventoryDTO) {
        return modelMapper.map(inventoryDTO, InventoryEntity.class);
    }

    public static List<InventoryEntity> toInventoryEntities(final List<InventoryCreateRequestDTO> inventories) {
        return inventories.stream()
                .map(InventoryDTOMapper::toInventoryEntity)
                .toList();
    }

    public static InventoryResponseDTO toInventoryResponseDTO(final InventoryEntity inventory) {
        final InventoryResponseDTO inventoryResponseDTO = modelMapper.map(inventory, InventoryResponseDTO.class);

        inventoryResponseDTO.setProductId(inventory.getProduct().getId());

        return inventoryResponseDTO;
    }
}
