package status200.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Orders")
public class Order {
    @Id
    private String id;

    private String customerId;

    private Double totalPrice;

    private String transactionDetails;

    private List<OrderItem> items;

    // Constructors, getters, setters, and other methods

    public Order() {
    }

    public Order(String id, String customerId, String transactionDetails, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.transactionDetails = transactionDetails;
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    public Double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalPrice=" + totalPrice +
                ", transactionDetails='" + transactionDetails + '\'' +
                ", items=" + items +
                '}';
    }
}