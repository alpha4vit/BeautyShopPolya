package by.polly.beatyshop.modules.product.api.controller;

import by.polly.beatyshop.modules.product.api.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/product/v1")
public interface ProductController {

    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Product> save(@RequestBody Product dto);

    @GetMapping
    ResponseEntity<List<Product>> getAllFiltered(
            @RequestParam(required = false) BigDecimal fromOriginalPrice,
            @RequestParam(required = false) BigDecimal toOriginalPrice
    );

    @GetMapping("/category/{category_id}")
    ResponseEntity<List<Product>> getAllByCategoryIdFiltered(
            @PathVariable("category_id") Long categoryId,
            @RequestParam(required = false) BigDecimal fromOriginalPrice,
            @RequestParam(required = false) BigDecimal toOriginalPrice);

}
