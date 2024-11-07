package by.polly.beatyshop.modules.product.api.controller.impl;

import by.polly.beatyshop.modules.product.api.dto.Product;
import by.polly.beatyshop.modules.product.service.ProductService;
import by.polly.beatyshop.modules.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductControllerImpl {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
        final var product = productService.getById(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @PostMapping
    public ResponseEntity<Product> save(Product dto) {
        log.info(productMapper.toEntity(dto).toString());
        final var saved = productService.save(productMapper.toEntity(dto));
        return ResponseEntity.ok(productMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        final var products = productService.getAll();
        return ResponseEntity.ok(productMapper.toDTOs(products));
    }

    @GetMapping("/category/{cat_id}")
    public ResponseEntity<List<Product>> getAllByCategoryIdFiltered(@PathVariable("cat_id") Long categoryId) {
        final var products = productService.getAllByCategoryId(categoryId);
        return ResponseEntity.ok(productMapper.toDTOs(products));
    }

}
