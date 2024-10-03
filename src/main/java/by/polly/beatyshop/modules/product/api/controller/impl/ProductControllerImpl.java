package by.polly.beatyshop.modules.product.api.controller.impl;

import by.polly.beatyshop.modules.product.api.controller.ProductController;
import by.polly.beatyshop.modules.product.api.dto.Product;
import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.service.ProductService;
import by.polly.beatyshop.modules.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<Product> getById(Long id) {
        final var product = productService.getById(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @Override
    public ResponseEntity<Product> save(Product dto) {
        log.info(productMapper.toEntity(dto).toString());
        final var saved = productService.save(productMapper.toEntity(dto));
        return ResponseEntity.ok(productMapper.toDTO(saved));
    }

    @Override
    public ResponseEntity<List<Product>> getAllFiltered(BigDecimal fromOriginalPrice, BigDecimal toOriginalPrice) {
        final var filter = new ProductCategoryFilterDto(fromOriginalPrice, toOriginalPrice, null);
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> getAllByCategoryIdFiltered(Long categoryId,
                                                                    BigDecimal fromOriginalPrice,
                                                                    BigDecimal toOriginalPrice) {
        final var filter = new ProductCategoryFilterDto(fromOriginalPrice, toOriginalPrice, categoryId);
        final var products = productService.getAllByCategoryId(filter);
        return ResponseEntity.ok(productMapper.toDTOs(products));
    }

}
