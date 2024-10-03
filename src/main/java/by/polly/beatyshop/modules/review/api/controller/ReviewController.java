package by.polly.beatyshop.modules.review.api.controller;

import by.polly.beatyshop.modules.review.api.dto.dto.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reviews/v1")
public interface ReviewController {

    @GetMapping("/products/{product_id}")
    List<Review> getAllByProduct(@PathVariable("product_id") Long productId,
                                 @RequestParam(value = "stars", required = false) Integer stars);

    @GetMapping("/{id}")
    Review getById(@PathVariable("id") Long id);

    @PostMapping
    Review save(@RequestBody Review review);

}
