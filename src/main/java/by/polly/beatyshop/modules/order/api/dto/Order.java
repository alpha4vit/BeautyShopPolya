package by.polly.beatyshop.modules.order.api.dto;

import by.polly.beatyshop.modules.product.api.dto.Product;

import java.util.List;

public record Order(

        Long id,

        Long userId,

        String orderState,

        Double sumPrice,

        List<Product> products
) {
}
