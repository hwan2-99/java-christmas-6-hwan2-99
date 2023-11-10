package christmas.model;

import christmas.constant.Menu;
import christmas.constant.Price;
import java.util.Map;
import java.util.Map.Entry;

public class Discount {
    private static final String DESSERT = "dessert";
    private static final String MAIN = "main";
    private static final int MINUS = -1;
    private final OrderItem orderItem;

    public Discount(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public int dailyDiscount(int price) {
        return price * MINUS;
    }

    public int weekDiscount(boolean isWeekDay) {
        int weekDiscountPrice;
        if (isWeekDay) {
            weekDiscountPrice = weekDayEventDiscount();
            return weekDiscountPrice;
        }
        weekDiscountPrice = weekendEventDiscount();
        return weekDiscountPrice;
    }

    public int specialDiscount(boolean isSpecialDay) {
        if (isSpecialDay) {
            return Price.INITIAL_DISCOUNT_PRICE.getPrice() * MINUS;
        }
        return 0;
    }

    private int weekDayEventDiscount() {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            Menu menu = Menu.getMenuByName(menuName);
            if (menu.getType() == DESSERT) {
                menuCount++;
            }
        }
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }

    private int weekendEventDiscount() {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            Menu menu = Menu.getMenuByName(menuName);
            if (menu.getType() == MAIN) {
                menuCount++;
            }
        }
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }
}
