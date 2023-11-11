package christmas.model;

import christmas.constant.Menu;
import christmas.constant.Price;
import java.util.Map;
import java.util.Map.Entry;

public class OrderItem {
    private final Map<String, Integer> orderInfo;

    public OrderItem(Map<String, Integer> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Map<String, Integer> getOrderInfo() {
        return orderInfo;
    }

    public int calculateOrderPrice() {
        int orderPrice = Price.NONE.getPrice();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(menuName);

            orderPrice += menu.getPrice() * quantity;
        }

        return orderPrice;
    }

    public boolean isOverEventPrice() {
        return calculateOrderPrice() >= Price.CHECK_CHAMPAGNE_GIVE.getPrice();
    }

    public boolean isApply() {
        return calculateOrderPrice() >= Price.EVENT_APPLY_PRICE.getPrice();
    }
}