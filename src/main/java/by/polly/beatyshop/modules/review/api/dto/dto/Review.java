package by.polly.beatyshop.modules.review.api.dto.dto;

public record Review(

        String id,

        Integer stars,

        String comment,

        Long productId,

        Long userId

) {
}
