package com.ecommerce.domain.seller;

import com.ecommerce.error.NotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class SellerError {

    public static <T> Supplier<NotFoundException> supplySellerNotFound(T input) {
        return () -> new NotFoundException("Seller with %s not found", input);
    }
}
