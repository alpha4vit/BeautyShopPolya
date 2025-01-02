package by.polly.beatyshop.modules.product.core.repository;

import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {
    List<ProductEntity> findAllByCategoryId(Long categoryId);

    List<ProductEntity> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM orders WHERE product_id = :productId; DELETE FROM favourites_products WHERE product_id = :productId; DELETE FROM product WHERE id = :productId", nativeQuery = true)
    void deleteById(@NotNull Long productId);
}
