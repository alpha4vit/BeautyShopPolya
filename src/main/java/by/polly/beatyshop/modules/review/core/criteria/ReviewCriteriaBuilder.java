package by.polly.beatyshop.modules.review.core.criteria;

import by.polly.beatyshop.modules.review.core.criteria.filter.ReviewFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ReviewCriteriaBuilder {

    public static Query getQuery(final ReviewFilter reviewFilter) {
        final var query = new Query();

        byStars(query, reviewFilter.getStars());
        byProductId(query, reviewFilter.getProductId());

        return query;
    }

    public static void byStars(final Query query, final Integer stars) {
        if (stars == null) {
            return;
        }

        query.addCriteria(Criteria.where("stars").is(stars));
    }

    public static void byProductId(final Query query, final Long productId) {
        if (productId == null) {
            return;
        }

        query.addCriteria(Criteria.where("productId").is(productId));
    }

}
