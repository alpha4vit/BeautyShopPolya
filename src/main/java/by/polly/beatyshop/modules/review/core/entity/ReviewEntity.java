package by.polly.beatyshop.modules.review.core.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("reviews")
public class ReviewEntity {

    @Id
    private String id;

    private Integer stars;

    private String comment;

    private Long productId;

    private Long userId;

}
