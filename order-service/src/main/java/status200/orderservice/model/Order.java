package status200.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String customerId;

    private Double totalPrice;

    private String transactionDetails;

    private List<OrderItem> items;

    public Order() {
    }

    public Order(String id, String customerId, Double totalPrice, String transactionDetails, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.transactionDetails = transactionDetails;
        this.items = items;
    }

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
