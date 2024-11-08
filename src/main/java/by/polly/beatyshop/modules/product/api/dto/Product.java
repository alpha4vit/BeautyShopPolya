package by.polly.beatyshop.modules.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Product(

        Long id,

        String name,

        String description,

        String applying,

        @JsonProperty(value = "original_price")
        Double originalPrice,

        @JsonProperty(value = "original_currency")
        String originalCurrency,

        String size,

        @JsonProperty(value = "measurement_type_id")
        Long measurementTypeId,

        String units,

        String image,

        @JsonProperty(value = "product_category")
        ProductCategory category,

        List<ImageResponse> images


) {
}
