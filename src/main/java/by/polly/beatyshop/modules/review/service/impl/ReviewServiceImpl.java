package by.polly.beatyshop.modules.review.service.impl;

import by.polly.beatyshop.modules.review.api.exception.exceptions.EntityNotFoundException;
import by.polly.beatyshop.modules.review.core.criteria.ReviewCriteriaBuilder;
import by.polly.beatyshop.modules.review.core.criteria.filter.ReviewFilter;
import by.polly.beatyshop.modules.review.core.entity.ReviewEntity;
import by.polly.beatyshop.modules.review.core.repository.ReviewRepository;
import by.polly.beatyshop.modules.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ReviewEntity save(final ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public ReviewEntity getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with this id not found!"));
    }

    @Override
    public void deleteById(Long id) {
        var review = getById(id);
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewEntity> getAllFilteredByProductId(final ReviewFilter reviewFilter) {
        final var query = ReviewCriteriaBuilder.getQuery(reviewFilter);
        return mongoTemplate.find(query, ReviewEntity.class);
    }
}
