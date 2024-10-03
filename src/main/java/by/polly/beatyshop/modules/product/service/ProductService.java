package by.polly.beatyshop.modules.product.service;

import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity getById(Long id);

    List<ProductEntity> getAll(ProductCategoryFilterDto filter);

    ProductEntity save(ProductEntity product);

    List<ProductEntity> getAllByCategoryId(ProductCategoryFilterDto filter);

}
