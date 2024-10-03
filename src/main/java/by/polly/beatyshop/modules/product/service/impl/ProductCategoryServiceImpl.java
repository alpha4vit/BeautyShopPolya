package by.polly.beatyshop.modules.product.service.impl;

import by.polly.beatyshop.modules.product.core.entity.ProductCategoryEntity;
import by.polly.beatyshop.modules.product.core.repository.ProductCategoryRepository;
import by.polly.beatyshop.modules.product.service.ProductCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductCategoryEntity save(final ProductCategoryEntity category) {
        if (category.getParent() == null)
            category.setParent(categoryRepository.getRootCategory());
        category.setTitle(StringUtils.normalizeSpace(category.getTitle()));
        return categoryRepository.save(category);
    }

    @Override
    @Cacheable(value = "sub_categories", key = "#parentCategoryId")
    public List<ProductCategoryEntity> getAllByParentId(final Long parentCategoryId) {
        var category = getById(parentCategoryId);
        return categoryRepository.findAllByParent(category);
    }

    @Override
    public ProductCategoryEntity getById(final Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with this id not found!"));
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        getById(id);
        categoryRepository.deleteById(id);
    }

}
