package status200.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import status200.orderservice.model.Order;
import status200.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    // Endpoint to fetch all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        LOGGER.info("Fetching all orders...");
        List<Order> orders = orderRepository.findAll();
        LOGGER.info("Fetched orders: {}", orders);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Endpoint to create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        LOGGER.info("Creating order: {}", order);
        BigDecimal totalPrice = calculateTotalPrice(order);
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        LOGGER.info("Order created: {}", savedOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Endpoint to fetch an order by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        LOGGER.info("Fetching order by id: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        LOGGER.info("Updating order with id: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> {
            BigDecimal totalPrice = calculateTotalPrice(updatedOrder);
            updatedOrder.setTotalPrice(totalPrice);
            updatedOrder.setId(id);
            orderRepository.save(updatedOrder);
            LOGGER.info("Order updated: {}", updatedOrder);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to delete an order by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        LOGGER.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
        LOGGER.info("Order deleted with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Method to calculate the total price of an order
    private BigDecimal calculateTotalPrice(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return order.getItems().stream()
                .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
