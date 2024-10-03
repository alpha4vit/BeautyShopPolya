package by.polly.beatyshop.modules.review.service;


import by.polly.beatyshop.modules.review.core.criteria.filter.ReviewFilter;
import by.polly.beatyshop.modules.review.core.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    ReviewEntity save(ReviewEntity review);

    ReviewEntity getById(Long id);

    void deleteById(Long id);

    List<ReviewEntity> getAllFilteredByProductId(ReviewFilter reviewFilter);

}
