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
    private final int visitDate;
    private final EventManager eventManager;

    public Discount(int visitDate, OrderItem orderItem, EventManager eventManager) {
        this.orderItem = orderItem;
        this.visitDate = visitDate;
        this.eventManager = eventManager;
    }

    public int calculateDiscountPrice() {
        return dailyDiscount() +
                weekDiscount() +
                specialDiscount() +
                bonusMenuDiscount();
    }

    public int calculateApplyDiscount() {
        return dailyDiscount() +
                weekDiscount() +
                specialDiscount();
    }

    public int dailyDiscount() {
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
        return menuCount * Price.DAILY_DISCOUNT.getPrice() * MINUS;
    }

    public int weekDiscount() {
        if (orderItem.isApply()) {
            if (eventManager.isWeekDay(visitDate)) {
                return discountByWeekType(DESSERT);
            }
            return discountByWeekType(MAIN);
        }
        return Price.NONE.getPrice();
    }

    public int specialDiscount() {
        if (orderItem.isApply()) {
            if (eventManager.isSpecialDay(visitDate)) {
                return Price.INITIAL_DISCOUNT.getPrice() * MINUS;
            }
            return Price.NONE.getPrice();
        }
        return Price.NONE.getPrice();
    }

    public int bonusMenuDiscount() {
        if (orderItem.isApply()) {
            if (orderItem.isOverEventPrice()) {
                return Menu.CHAMPAGNE.getPrice() * MINUS;
            }
            return Price.NONE.getPrice();
        }
        return Price.NONE.getPrice();
    }

    public boolean hasDiscount() {
        return dailyDiscount() != Price.NONE.getPrice() || weekDiscount() != Price.NONE.getPrice()
                || specialDiscount() != Price.NONE.getPrice();
    }
}
