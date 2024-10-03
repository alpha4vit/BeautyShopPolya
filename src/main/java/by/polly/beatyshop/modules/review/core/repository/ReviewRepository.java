package by.polly.beatyshop.modules.review.core.repository;

import by.polly.beatyshop.modules.review.core.entity.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByProductId(Long productId);

}
