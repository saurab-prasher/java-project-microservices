package status200.orderservice.runner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import status200.orderservice.model.Order;
import status200.orderservice.model.OrderItem;
import status200.orderservice.repository.OrderItemRepository;
import status200.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Sample Order 1
        Order order1 = new Order(null, "customer1", "Transaction details 1", null);
        order1 = orderRepository.save(order1);

        OrderItem item1 = new OrderItem(null, order1.getId(), "product1", "Product 1", 2, 69.99);
        OrderItem item2 = new OrderItem(null, order1.getId(), "product2", "Product 2", 1, 49.99);
        List<OrderItem> items1 = Arrays.asList(item1, item2);
        orderItemRepository.saveAll(items1);

        // Sample Order 2
        Order order2 = new Order(null, "customer2", "Transaction details 2", null);
        order2 = orderRepository.save(order2);

        OrderItem item3 = new OrderItem(null, order2.getId(), "product3", "Product 3", 3, 89.99);
        List<OrderItem> items2 = Arrays.asList(item3);
        orderItemRepository.saveAll(items2);
    }
}