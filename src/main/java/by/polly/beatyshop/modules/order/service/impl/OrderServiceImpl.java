package by.polly.beatyshop.modules.order.service.impl;

import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import by.polly.beatyshop.modules.order.core.repository.OrderRepository;
import by.polly.beatyshop.modules.order.service.OrderService;
import by.polly.beatyshop.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public OrderEntity addToOrder(final Long userId, final ProductAddRequest request) {
        var product = productService.getById(request.productId());

        var order = OrderEntity.builder()
                .sumPrice(product.getOriginalPrice())
                .product(product)
                .userId(userId)
                .number(request.number())
                .fio(request.fio())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();

        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getOrderedByUser(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().sorted().toList();
    }

    @Override
    public List<OrderEntity> getByUserId(Long userId) {
        var orders = orderRepository.findAllByUserId(userId);
        if (orders.isEmpty())
            throw new EntityNotFoundException(String.format("Cart for user %s not found", userId.toString()));

        return orders;
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
    public OrderEntity getById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        orderRepository.deleteById(id);
    }

    @Override
    public OrderEntity update(Long id, OrderEntity order) {
        var orderForUpdate = getByUserId(id);
//        order.setId(orderForUpdate.getId()); TODO
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<OrderEntity> getAllByProductId(final Long productId) {
        return orderRepository.findAllByProductId(productId);
    }
}
