package by.polly.beatyshop.modules.product.service;

import by.polly.beatyshop.modules.product.core.entity.ProductCategoryEntity;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryEntity save(ProductCategoryEntity category);

    List<ProductCategoryEntity> getAllByParentId(Long parentCategoryId);

    ProductCategoryEntity getById(Long id);

    void deleteById(Long id);

}
