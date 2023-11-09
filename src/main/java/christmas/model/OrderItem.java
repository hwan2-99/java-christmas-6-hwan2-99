package christmas.model;

import java.util.Map;

public class OrderItem {
    private final Map<String, Integer> orderInfo;

    public OrderItem(Map<String, Integer> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Map<String, Integer> getOrderInfo() {
        return orderInfo;
    }

}
