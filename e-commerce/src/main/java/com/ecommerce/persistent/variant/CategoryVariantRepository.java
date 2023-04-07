package com.ecommerce.persistent.variant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryVariantRepository extends JpaRepository<CategoryVariantEntity, UUID> {

    @Query("select c from CategoryVariantEntity c where c.category.categoryName = :categoryName")
    List<CategoryVariantEntity> findByCategoryName(final String categoryName);

    Optional<CategoryVariantEntity> findByVariantName(final String variantName);
}
