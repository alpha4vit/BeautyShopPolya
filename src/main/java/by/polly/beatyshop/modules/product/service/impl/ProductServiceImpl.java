package by.polly.beatyshop.modules.product.service.impl;

import by.polly.beatyshop.modules.core.images.ImageServiceStorage;
import by.polly.beatyshop.modules.product.api.dto.Image;
import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import by.polly.beatyshop.modules.product.core.repository.ProductRepository;
import by.polly.beatyshop.modules.product.core.specification.ProductSpecification;
import by.polly.beatyshop.modules.product.service.ProductCategoryService;
import by.polly.beatyshop.modules.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final ImageServiceStorage imageServiceStorage;
    private final ObjectMapper objectMapper;

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
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public ProductEntity save(Long userId, ProductEntity product) {
        final var category = productCategoryService.getById(product.getCategory().getId());
        product.setCategory(category);
        product.setUserId(userId);
        product.setOptions(product.getOptions());
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllByCategoryId(final Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    @SneakyThrows
    public List<String> uploadImages(final Long productId, final MultipartFile image) {
        var saved = new ArrayList<String>();
        String imageName = imageServiceStorage.uploadToStorage(new Image(image));
        saved.add(imageName);
        var product = getById(productId);
        product.setImages(objectMapper.writeValueAsString(saved));
        return saved;
    }
}
