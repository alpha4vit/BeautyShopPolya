package by.polly.beatyshop.modules.order.api.controller;

import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order/v1")
public interface OrderController {

    @GetMapping("/{id}")
    ResponseEntity<Order> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Order> save(@RequestBody final Order dto);

    @PostMapping("/{id}")
    ResponseEntity<Order> addProductToOrder(@PathVariable("id") Long orderId,
                                                   @RequestBody final ProductAddRequest productAddRequest);

    @GetMapping
    ResponseEntity<List<Order>> getAll();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@PathVariable("id") Long orderId);

    @GetMapping("/user/{user_id}")
    ResponseEntity<List<Order>> getAllByUserId(@PathVariable("user_id") Long userId);

}
