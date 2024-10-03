package by.polly.beatyshop.modules.product.api.dto;

public record ProductCategory(

        Long id,

        String title,

        String image,

        Long parentId

) {
}
