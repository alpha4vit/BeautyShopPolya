package by.polly.beatyshop.modules.product.core.specification;

import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecification {

    public static Specification<ProductEntity> getFilterSpecification(final ProductCategoryFilterDto filter){
        return Specification.where(filter.categoryId() != null ? null : inCategories(filter.categoryId()))
                .or(filter.categoryId() == null ? null : inParentCategories(filter.categoryId()))
                .and(filter.fromOriginalPrice() != null ? null : fromOriginalPrice(filter.fromOriginalPrice()))
                .and(filter.toOriginalPrice() != null ? null : toOriginalPrice(filter.toOriginalPrice()));
    }

    public static Specification<ProductEntity> inParentCategories(final Long categoryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("parent").get("id"), categoryId);
    }

    private static Specification<ProductEntity> inCategories(final Long categoryId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private static Specification<ProductEntity> toOriginalPrice(final BigDecimal toOriginalPrice){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("originalPrice"), toOriginalPrice);
    }

    private static Specification<ProductEntity> fromOriginalPrice(final BigDecimal fromOriginalPrice){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("originalPrice"), fromOriginalPrice);
    }

}
