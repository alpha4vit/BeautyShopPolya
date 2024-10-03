package by.polly.beatyshop.modules.product.service.mapper;

import by.polly.beatyshop.modules.product.api.dto.Product;
import by.polly.beatyshop.modules.product.config.BaseMapperConfig;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        config = BaseMapperConfig.class,
        uses = {
                ProductCategoryMapper.class
        }
)
public interface ProductMapper {

    @Mapping(target = "measurementTypeId", source = "measurementType.id")
    Product toDTO(ProductEntity entity);

    @Mapping(target = "measurementType.id", source = "measurementTypeId")
    ProductEntity toEntity(Product dto);

    List<Product> toDTOs(List<ProductEntity> products);

    List<ProductEntity> toEntities(List<Product> dtos);
}
