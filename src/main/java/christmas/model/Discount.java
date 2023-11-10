package christmas.model;

import christmas.constant.Menu;
import christmas.constant.Price;
import java.util.Map;
import java.util.Map.Entry;

public class Discount {
    private static final String DESSERT = "dessert";
    private static final int MINUS = -1;
    private int discountPrice;
    private final OrderItem orderItem;

    public Discount(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public int calculateDiscount(boolean isWeekDay) {
        return weekDayEventDiscount(isWeekDay);
    }

    private int weekDayEventDiscount(boolean isWeekday) {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();
        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(menuName);

            if (menu.getType() == DESSERT) {
                menuCount++;
            }
        }
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }
}
