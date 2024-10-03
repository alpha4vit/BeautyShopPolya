package by.polly.beatyshop.modules.review.core.criteria.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFilter {

    private Integer stars;

    private Long productId;

}
