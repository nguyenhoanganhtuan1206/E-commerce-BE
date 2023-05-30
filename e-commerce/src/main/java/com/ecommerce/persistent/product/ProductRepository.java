package com.ecommerce.persistent.product;

import com.ecommerce.persistent.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("select p from ProductEntity p where p.seller.user.id = :userId ")
    List<ProductEntity> findByUserId(final UUID userId);

    Optional<ProductEntity> findByNameContainingIgnoreCase(final String productName);

    @Query(value = "select p from ProductEntity p where p.name like %:searchTemp% or p.seller.sellerName like %:searchTemp%")
    List<ProductEntity> findByNameOrSellerName(final String searchTemp);


    @Query(value = "select p from ProductEntity p" +
            " inner join p.inventories i " +
            " where i.quantity = 0 and p.quantity = 0 and p.seller.id = :sellerId ")
    List<ProductEntity> findProductWithOutOfStockAndSellerId(final UUID sellerId);

    @Query(value = "select p from ProductEntity p" +
            " inner join p.inventories i " +
            " where i.quantity > 0 or p.quantity > 0 and p.seller.id = :sellerId ")
    List<ProductEntity> findProductWithInStockAndSellerId(final UUID sellerId);

    @Query(value = "select p from ProductEntity p where p.productApproval = :status and p.seller.id = :sellerId ")
    List<ProductEntity> findProductWithStatusAndSellerId(final Status status, final UUID sellerId);
}
