package by.polly.beatyshop.modules.order.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order (

        Long id,

        Long userId,

        @JsonProperty("order_state")
        String orderState,

        @JsonProperty("sum_price")
        Double sumPrice
) {
}
