package by.polly.beatyshop.modules.order.api.dto;

import by.polly.beatyshop.modules.product.api.dto.Product;

import java.time.Instant;
import java.util.List;

public record Order(

        Long id,

        Long userId,

        Double sumPrice,

        Instant startDate,

        Instant endDate,

        Product product
) {
}
