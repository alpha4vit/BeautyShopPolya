package by.polly.beatyshop.modules.product.api.dto;

import java.math.BigDecimal;

public record ProductCategoryFilterDto(

        BigDecimal fromOriginalPrice,

        BigDecimal toOriginalPrice,

        Long categoryId

) {
}
