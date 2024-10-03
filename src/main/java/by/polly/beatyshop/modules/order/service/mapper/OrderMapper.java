package by.polly.beatyshop.modules.order.service.mapper;


import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toDTO(OrderEntity entity);

    OrderEntity toEntity(Order dto);

    List<Order> toDTOs(List<OrderEntity> products);

    List<OrderEntity> toEntities(List<Order> dtos);

}
