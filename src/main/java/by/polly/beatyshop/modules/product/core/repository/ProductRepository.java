package by.polly.beatyshop.modules.product.core.repository;

import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {
    List<ProductEntity> findAllByCategoryId(Long categoryId);
}
