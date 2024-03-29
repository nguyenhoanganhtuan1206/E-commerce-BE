package com.ecommerce.domain.product.mapper;

import com.ecommerce.api.product.dto.ProductResponseDTO;
import com.ecommerce.domain.inventory.dto.InventoryResponseDTO;
import com.ecommerce.domain.inventory.mapper.InventoryDTOMapper;
import com.ecommerce.persistent.category.CategoryEntity;
import com.ecommerce.persistent.payment_method.PaymentMethodEntity;
import com.ecommerce.persistent.product.ProductEntity;
import com.ecommerce.persistent.style.ProductStyleEntity;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;

@UtilityClass
public class ProductCreateDTOMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public static ProductResponseDTO toProductResponseDTO(final ProductEntity entity) {
        final ProductResponseDTO productDTO = modelMapper.map(entity, ProductResponseDTO.class);

        productDTO.setBrandName(entity.getBrand().getBrandName());
        productDTO.setVariantName(entity.getCategoryVariant().getName());

        final List<String> categoryNames = entity.getCategories().stream()
                .map(CategoryEntity::getCategoryName)
                .toList();
        productDTO.setCategories(categoryNames);

        final List<String> paymentMethodsName = entity.getPaymentMethods().stream()
                .map(PaymentMethodEntity::getName)
                .toList();
        productDTO.setPaymentMethods(paymentMethodsName);

        final List<String> productStylesName = entity.getProductStyles().stream()
                .map(ProductStyleEntity::getName)
                .toList();
        productDTO.setProductStyles(productStylesName);

        if (entity.getInventories() != null) {
            final List<InventoryResponseDTO> inventories = entity.getInventories().stream()
                    .map(InventoryDTOMapper::toInventoryResponseDTO)
                    .toList();
            productDTO.setInventories(inventories);
        }

        return productDTO;
    }

    public static List<ProductResponseDTO> toProductResponseDTOs(final List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductCreateDTOMapper::toProductResponseDTO)
                .toList();
    }
}
