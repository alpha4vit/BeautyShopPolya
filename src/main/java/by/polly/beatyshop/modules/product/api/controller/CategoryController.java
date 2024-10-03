package by.polly.beatyshop.modules.product.api.controller;

import by.polly.beatyshop.modules.product.api.dto.ProductCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category/v1")
public interface CategoryController {

    @GetMapping("/{id}")
    ResponseEntity<ProductCategory> getById(@PathVariable("id") Long id);

    @GetMapping("/parent/{parent_id}")
    ResponseEntity<List<ProductCategory>> getAllByParentId(@PathVariable("parent_id") Long parentId);

    @PostMapping
    ResponseEntity<ProductCategory> save(@RequestBody ProductCategory category);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@PathVariable("id") Long id);

}
