package by.polly.beatyshop.modules.order.service.mapper;


import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import by.polly.beatyshop.modules.product.config.BaseMapperConfig;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface OrderMapper {

    Order toDTO(OrderEntity entity);

    OrderEntity toEntity(Order dto);

    List<Order> toDTOs(List<OrderEntity> value);

    List<OrderEntity> toEntities(List<Order> value);

}
