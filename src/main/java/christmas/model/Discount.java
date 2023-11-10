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

    public int calculateDiscountPrice(int visitDate, EventManager eventManager) {

        return dailyDiscount(eventManager.getCalender().get(visitDate)) + weekDiscount(
                eventManager.isWeekDay(visitDate))
                + specialDiscount(eventManager.isSpecialDay(visitDate)) + bonusMenuDiscount(orderItem.overEventPrice());
    }

    public int dailyDiscount(int price) {
        return price * MINUS;
    }

    public int weekDiscount(boolean isWeekDay) {
        if (isWeekDay) {
            return weekDayEventDiscount();
        }
        return weekendEventDiscount();
    }

    private int weekDayEventDiscount() {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(menuName);
            if (menu.getType() == DESSERT) {
                menuCount += quantity;
            }
        }
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }

    private int weekendEventDiscount() {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(menuName);
            if (menu.getType() == MAIN) {
                menuCount += quantity;
            }
        }
        System.out.println(menuCount);
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }

    public int specialDiscount(boolean isSpecialDay) {
        if (isSpecialDay) {
            return Price.INITIAL_DISCOUNT_PRICE.getPrice() * MINUS;
        }
        return Price.NONE.getPrice();
    }

    public int bonusMenuDiscount(boolean isOverEventPrice) {
        if (isOverEventPrice) {
            return Menu.CHAMPAGNE.getPrice() * MINUS;
        }
        return Price.NONE.getPrice();
    }
}
