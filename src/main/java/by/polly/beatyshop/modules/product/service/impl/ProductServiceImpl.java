package by.polly.beatyshop.modules.product.service.impl;

import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import by.polly.beatyshop.modules.product.core.repository.ProductRepository;
import by.polly.beatyshop.modules.product.core.specification.ProductSpecification;
import by.polly.beatyshop.modules.product.service.MeasurementTypeService;
import by.polly.beatyshop.modules.product.service.ProductCategoryService;
import by.polly.beatyshop.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final MeasurementTypeService measurementTypeService;

    @Override
    public ProductEntity getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with this id not found!"));
    }

    @Override
    public List<ProductEntity> getAll(final ProductCategoryFilterDto filter) {
        final var specification = ProductSpecification.getFilterSpecification(filter);
        return productRepository.findAll(specification);
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity product) {
        final var category = productCategoryService.getById(product.getCategory().getId());
        final var measurementType = measurementTypeService.getById(product.getMeasurementType().getId());
        product.setCategory(category);
        product.setMeasurementType(measurementType);
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllByCategoryId(final ProductCategoryFilterDto filter) {
        final var specification = ProductSpecification.getFilterSpecification(filter);
        return productRepository.findAll(specification);
    }
}
