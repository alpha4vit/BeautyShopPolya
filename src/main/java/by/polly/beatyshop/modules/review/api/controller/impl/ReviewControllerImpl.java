package by.polly.beatyshop.modules.review.api.controller.impl;

import by.polly.beatyshop.modules.review.api.controller.ReviewController;
import by.polly.beatyshop.modules.review.api.dto.dto.Review;
import by.polly.beatyshop.modules.review.service.ReviewService;
import by.polly.beatyshop.modules.review.service.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Override
    public List<Review> getAllByProduct(Long productId, Integer stars){
        var reviews = reviewService.getAll();
        return reviewMapper.toDtos(reviews);
    }

    @Override
    public Review getById(Long id){
        var review = reviewService.getById(id);
        return reviewMapper.toDto(review);
    }

    @Override
    public Review save(Review review){
        var saved = reviewService.save(reviewMapper.toEntity(review));
        return reviewMapper.toDto(saved);
    }

}
