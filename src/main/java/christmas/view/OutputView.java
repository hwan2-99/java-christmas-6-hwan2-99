package christmas.view;

import christmas.constant.Badge;
import christmas.constant.Menu;
import christmas.constant.Price;
import christmas.constant.message.OutputMessage;
import christmas.model.Discount;
import christmas.model.EventBadge;
import christmas.model.OrderItem;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map.Entry;

public class OutputView {
    private static final int BONUS_COUNT = 1;
    private static final String COUNT = "개";
    private static final String NONE = "없음";
    private static final String WON = "원";
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    public void outputDateBenefitsMessage(int visitDate) {
        String message = String.format(OutputMessage.PRINT_EVENT_BENEFITS.getMessage(), visitDate);

        System.out.println(message);
    }

    public void outputOrderDetails(OrderItem orderItem) {
        outputOrderMenus(orderItem);
        outputOrderPrice(orderItem);
        outputBonusMenu(orderItem);
    }

    private void outputOrderMenus(OrderItem orderItem) {
        System.out.println("\n" + OutputMessage.ORDER_LIST.getMessage());
        for (Entry<String, Integer> entry : orderItem.getOrderInfo().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + COUNT);
        }
    }

    private void outputOrderPrice(OrderItem orderItem) {
        System.out.println("\n" + OutputMessage.ORDER_PRICE_BEFORE_DISCOUNT.getMessage());
        System.out.println(numberFormat.format(orderItem.calculateOrderPrice()) + WON);
    }

    private void outputBonusMenu(OrderItem orderItem) {
        System.out.println("\n" + OutputMessage.BONUS_MENU.getMessage());
        if (orderItem.isOverEventPrice()) {
            System.out.println(Menu.CHAMPAGNE.getName() + " " + BONUS_COUNT + COUNT);
            return;
        }
        System.out.println(NONE);
    }

    public void outputDiscountDetails(Discount discount, boolean isWeekDay) {
        System.out.println("\n" + OutputMessage.DISCOUNT_LIST.getMessage());

        if (discount.hasDiscount()) {
            outputDailyDiscount(discount.dailyDiscount());
            outputWeekDiscount(discount.weekDiscount(), isWeekDay);
            outputSpecialDiscount(discount.specialDiscount());
            outputBonusMenuDiscount(discount.bonusMenuDiscount());
            return;
        }
        outputNoneDiscount();
    }

    public void outputDailyDiscount(int discount) {
        String message = String.format(OutputMessage.DAILY_DISCOUNT.getMessage(), numberFormat.format(discount));
        System.out.println(message);
    }

    private void outputWeekDiscount(int discount, boolean isWeekDay) {
        if (isWeekDay) {
            outputWeekDayDiscount(discount);
            return;
        }
        outputWeekendDiscount(discount);
    }

    public void outputWeekDayDiscount(int discount) {
        if (discount == Price.NONE.getPrice()) {
            return;
        }
        String message = String.format(OutputMessage.WEEK_DAY_DISCOUNT.getMessage(), numberFormat.format(discount));
        System.out.println(message);
    }

    public void outputWeekendDiscount(int discount) {
        if (discount == Price.NONE.getPrice()) {
            return;
        }
        String message = String.format(OutputMessage.WEEKEND_DISCOUNT.getMessage(), numberFormat.format(discount));
        System.out.println(message);
    }

    public void outputSpecialDiscount(int discount) {
        if (discount == Price.NONE.getPrice()) {
            return;
        }
        String message = String.format(OutputMessage.SPECIAL_DISCOUNT.getMessage(), numberFormat.format(discount));
        System.out.println(message);
    }

    public void outputBonusMenuDiscount(int discount) {
        if (discount == Price.NONE.getPrice()) {
            return;
        }
        String message = String.format(OutputMessage.BONUS_MENU_DISCOUNT.getMessage(), numberFormat.format(discount));
        System.out.println(message);
    }

    public void outputNoneDiscount() {
        System.out.println(NONE);
    }

    public void outputAllDiscountPrice(Discount discount) {
        System.out.println("\n" + OutputMessage.ALL_DISCOUNT_PRICE.getMessage());
        System.out.println(numberFormat.format(discount.calculateDiscountPrice()) + WON);
    }

    public void outputExpectedPrice(int price) {
        System.out.println("\n" + OutputMessage.EXPECTED_PRICE.getMessage());
        System.out.println(numberFormat.format(price) + WON);
    }

    public void outputEventBadge(EventBadge eventBadge) {
        System.out.println("\n" + OutputMessage.DECEMBER_BADGE.getMessage());
        System.out.println(eventBadge.getBadgeName());

    }
}
