package by.polly.beatyshop.modules.review.core.repository;

import by.polly.beatyshop.modules.review.core.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByProductId(Long productId);

}
