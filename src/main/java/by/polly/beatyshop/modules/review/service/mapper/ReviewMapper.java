package by.polly.beatyshop.modules.review.service.mapper;

import by.polly.beatyshop.modules.review.api.dto.dto.Review;
import by.polly.beatyshop.modules.review.core.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ReviewMapper {

    Review toDto(ReviewEntity entity);

    ReviewEntity toEntity(Review dto);

    List<Review> toDtos(List<ReviewEntity> entities);

    List<ReviewEntity> toEntities(List<Review> dtos);

}
