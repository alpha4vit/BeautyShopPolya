package by.polly.beatyshop.modules.order.service.impl;

import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import by.polly.beatyshop.modules.order.core.entity.OrderState;
import by.polly.beatyshop.modules.order.core.repository.OrderRepository;
import by.polly.beatyshop.modules.order.service.OrderService;
import by.polly.beatyshop.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public OrderEntity addToOrder(final Long userId, final ProductAddRequest request) {
        var product = productService.getById(request.productId());
        var carts = orderRepository.findAllByUserIdAndOrderState(userId, OrderState.IN_PROGRESS);

        if (carts.isEmpty()) {
            return save(OrderEntity.builder()
                    .orderState(OrderState.IN_PROGRESS)
                    .sumPrice(product.getOriginalPrice())
                    .products(List.of(product))
                    .userId(userId)
                    .build());
        }

        var order = carts.getFirst();
        order.getProducts().add(product);
        order.setSumPrice(order.getSumPrice() + product.getOriginalPrice());

        return orderRepository.save(order);
    }

    @Override
    public OrderEntity getByUserId(Long userId) {
        var orders =  orderRepository.findAllByUserIdAndOrderState(userId, OrderState.IN_PROGRESS);
        if (orders.isEmpty())
            throw new EntityNotFoundException(String.format("Cart for user %s not found", userId.toString()));

        return orders.getFirst();
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        getByUserId(id);
        orderRepository.deleteById(id);
    }

    @Override
    public OrderEntity update(Long id, OrderEntity order) {
        var orderForUpdate = getByUserId(id);
        order.setId(orderForUpdate.getId());
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
