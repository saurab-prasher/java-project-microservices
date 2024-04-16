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
        OrderItem item1 = new OrderItem(null, null, "product1", "Product 1", 2, 69.99);
        OrderItem item2 = new OrderItem(null, null, "product2", "Product 2", 1, 49.99);
        List<OrderItem> items1 = Arrays.asList(item1, item2);
        Order order1 = new Order(null, "customer1", "Transaction details 1", items1);
        order1 = orderRepository.save(order1);  // Save order and get the saved order with ID

        // Set the order for each item
        for (OrderItem item : items1) {
            item.setOrder(order1);
            orderItemRepository.save(item);  // Save the updated item
        }

        // Sample Order 2
        OrderItem item3 = new OrderItem(null, null, "product3", "Product 3", 3, 89.99);
        List<OrderItem> items2 = Arrays.asList(item3);
        Order order2 = new Order(null, "customer2", "Transaction details 2", items2);
        order2 = orderRepository.save(order2);  // Save order and get the saved order with ID

        // Set the order for each item
        for (OrderItem item : items2) {
            item.setOrder(order2);
            orderItemRepository.save(item);  // Save the updated item
        }
    }
}