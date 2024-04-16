package status200.orderservice.services;

import status200.orderservice.model.Order;
import status200.orderservice.model.OrderItem;
import status200.orderservice.repository.OrderItemRepository;
import status200.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import status200.productservice.model.Product;
import status200.productservice.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;


    @Transactional
    public Order placeOrder(String customerId, List<OrderItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItem item : items) {
            Product product = productService.getProductById(item.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found");
            }

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }

            BigDecimal price = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(price);

            orderItems.add(new OrderItem(null, null, item.getProductId(), product.getName(), item.getQuantity(), price));

            // Update product stock
            productService.updateProductStock(item.getProductId(), product.getStock() - item.getQuantity());
        }

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setTotalPrice(totalPrice);
        order.setTransactionDetails("Transaction Successful");
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product getProductById(String id) {
        return productService.getProductById(id);
    }

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public Product updateProduct(String id, Product product) {
        return productService.updateProduct(id, product);
    }

    public void deleteProduct(String id) {
        productService.deleteProduct(id);
    }
}