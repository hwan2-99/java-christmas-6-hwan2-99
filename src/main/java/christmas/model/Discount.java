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
    private final EventManager eventManager;

    public Discount(OrderItem orderItem, EventManager eventManager) {
        this.orderItem = orderItem;
        this.eventManager = eventManager;
    }

    public int calculateDiscountPrice(int visitDate) {
        return dailyDiscount(visitDate) +
                weekDiscount(visitDate) +
                specialDiscount(visitDate) +
                bonusMenuDiscount(orderItem.isOverEventPrice());
    }

    public int dailyDiscount(int visitDate) {
        if (orderItem.isApply()) {
            return eventManager.getCalender().get(visitDate) * MINUS;
        }
        return Price.NONE.getPrice();
    }

    private int discountByWeekType(String menuType) {
        int menuCount = 0;
        Map<String, Integer> orderInfo = orderItem.getOrderInfo();

        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(menuName);

            if (menu.getType().equals(menuType)) {
                menuCount += quantity;
            }
        }
        return menuCount * Price.DAILY_DISCOUNT_PRICE.getPrice() * MINUS;
    }

    public int weekDiscount(int visitDate) {
        if (orderItem.isApply()) {
            if (eventManager.isWeekDay(visitDate)) {
                return discountByWeekType(DESSERT);
            }
            return discountByWeekType(MAIN);
        }
        return Price.NONE.getPrice();
    }

    public int specialDiscount(int visitDate) {
        if (orderItem.isApply()) {
            if (eventManager.isSpecialDay(visitDate)) {
                return Price.INITIAL_DISCOUNT_PRICE.getPrice() * MINUS;
            }
            return Price.NONE.getPrice();
        }
        return Price.NONE.getPrice();
    }

    public int bonusMenuDiscount(boolean isOverEventPrice) {
        if (orderItem.isApply()) {
            if (isOverEventPrice) {
                return Menu.CHAMPAGNE.getPrice() * MINUS;
            }
            return Price.NONE.getPrice();
        }
        return Price.NONE.getPrice();
    }
}
