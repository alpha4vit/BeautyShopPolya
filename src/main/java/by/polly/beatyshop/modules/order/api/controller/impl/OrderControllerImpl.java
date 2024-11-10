package by.polly.beatyshop.modules.order.api.controller.impl;

import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.service.OrderService;
import by.polly.beatyshop.modules.order.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<Order> getById(@PathVariable("userId") Long userId) {
        final var product = orderService.getByUserId(userId);
        return ResponseEntity.ok(orderMapper.toDTO(product));
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addProductToOrder(@PathVariable("userId") Long userId, @RequestBody ProductAddRequest productAddRequest) {
        var res = orderService.addToOrder(userId, productAddRequest);
        return ResponseEntity.ok(orderMapper.toDTO(res));
    }

    @PostMapping(value = "/{userId}/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> pay(@PathVariable("userId") Long userId) {
        var res = orderService.pay(userId);
        return ResponseEntity.ok(orderMapper.toDTO(res));
    }

    @DeleteMapping(value = "/{userId}/{productId}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        var res = orderService.removeFromOrder(userId, productId);
        return ResponseEntity.ok(orderMapper.toDTO(res));
    }
}
