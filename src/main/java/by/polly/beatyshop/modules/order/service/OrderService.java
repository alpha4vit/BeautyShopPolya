package by.polly.beatyshop.modules.order.service;


import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity addToOrder(final Long userId, final ProductAddRequest request);


    List<OrderEntity> getOrderedByUser(Long userId);

    List<OrderEntity> getByUserId(Long userId);

    List<OrderEntity> getAll();

    OrderEntity save(OrderEntity order);

    OrderEntity getById(Long id);

    void deleteById(Long id);

    OrderEntity update(Long id, OrderEntity order);

    List<OrderEntity> getAllByUserId(Long userId);

}
