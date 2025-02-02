package by.polly.beatyshop.modules.product.service.mapper;

import by.polly.beatyshop.modules.order.service.mapper.OrderMapper;
import by.polly.beatyshop.modules.product.api.dto.Product;
import by.polly.beatyshop.modules.product.config.BaseMapperConfig;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        config = BaseMapperConfig.class,
        uses = {
                ProductCategoryMapper.class,
                OrderMapper.class
        }
)
public interface ProductMapper {

    Product toDTO(ProductEntity entity);

    ProductEntity toEntity(Product dto);

    List<Product> toDTOs(List<ProductEntity> products);

    List<ProductEntity> toEntities(List<Product> dtos);
}
