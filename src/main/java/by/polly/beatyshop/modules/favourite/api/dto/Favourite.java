package by.polly.beatyshop.modules.favourite.api.dto;

import by.polly.beatyshop.modules.product.api.dto.Product;

import java.util.List;

public record Favourite(

        Long id,

        Long userId,

        String favouriteState,

        Double sumPrice,

        List<Product> products
) {
}
