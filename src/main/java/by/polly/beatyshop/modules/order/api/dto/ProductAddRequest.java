package by.polly.beatyshop.modules.order.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductAddRequest(

        @JsonProperty("product_id")
        Long productId,

        Integer amount

) {
}
