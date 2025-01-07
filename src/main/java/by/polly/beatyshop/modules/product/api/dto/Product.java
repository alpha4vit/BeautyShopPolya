package by.polly.beatyshop.modules.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.annotation.Order;

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

        Long square,

        @JsonProperty(value = "measurement_type_id")
        Long measurementTypeId,

        @JsonProperty(value = "room_count")
        Long roomCount,

        String image,

        String options,

        @JsonProperty(value = "product_category")
        ProductCategory category,

        String images,

        @JsonProperty("contact_number")
        String contactNumber
) {
}
