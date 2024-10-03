package by.polly.beatyshop.modules.product.api.controller.impl;

import by.polly.beatyshop.modules.product.api.controller.CategoryController;
import by.polly.beatyshop.modules.product.api.dto.ProductCategory;
import by.polly.beatyshop.modules.product.service.ProductCategoryService;
import by.polly.beatyshop.modules.product.service.mapper.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public ResponseEntity<ProductCategory> getById(Long id) {
        var category = productCategoryService.getById(id);
        return ResponseEntity.ok(productCategoryMapper.toDTO(category));
    }

    @Override
    public ResponseEntity<List<ProductCategory>> getAllByParentId(Long parentId) {
        var categories = productCategoryService.getAllByParentId(parentId);
        return ResponseEntity.ok(productCategoryMapper.toDTOs(categories));
    }

    @Override
    public ResponseEntity<ProductCategory> save(ProductCategory category) {
        var saved = productCategoryService.save(productCategoryMapper.toEntity(category));
        return ResponseEntity.ok(productCategoryMapper.toDTO(saved));
    }

    @Override
    public void deleteById(Long id) {
        productCategoryService.deleteById(id);
    }

}
