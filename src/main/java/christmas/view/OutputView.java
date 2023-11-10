package christmas.view;

import christmas.constant.Menu;
import christmas.model.OrderItem;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String PRINT_EVENT_BENEFITS = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_LIST = "<주문 메뉴>";
    private static final String ORDER_PRICE_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String BONUS_MENU = "<증정 메뉴>";
    private static final String DISCOUNT_LIST = "<혜택 내역>";
    private static final String DAILY_DISCOUNT= "크리스마스 디데이 할인: %s원";
    private static final String WEEK_DAY_DISCOUNT= "평일 할인: %s원";
    private static final int BONUS_COUNT = 1;
    private static final int NONE_DISCOUNT = 0;
    private static final String COUNT = "개";
    private static final String NONE = "없음";
    private static final String WON = "원";

    public void outputDateBenefitsMessage(int visitDate) {
        String message = String.format(PRINT_EVENT_BENEFITS, visitDate);

        System.out.println(message);
    }

    public void outputOrderMenus(OrderItem orderItem) {
        System.out.println("\n" + ORDER_LIST);
        for (Entry<String, Integer> entry : orderItem.getOrderInfo().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + COUNT);
        }
    }

    public void outputOrderPrice(int orderPrice) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        System.out.println("\n" + ORDER_PRICE_BEFORE_DISCOUNT);
        System.out.println(numberFormat.format(orderPrice) + WON);
    }

    public void outputBonusMenu(boolean overEventPrice) {
        System.out.println("\n" + BONUS_MENU);
        if (overEventPrice) {
            System.out.println(Menu.CHAMPAGNE.getName() + " " + BONUS_COUNT + COUNT);
            return;
        }
        System.out.println(NONE);
    }

    public void outputDiscountList(int discount) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        System.out.println("\n" + DISCOUNT_LIST);
        if (discount == NONE_DISCOUNT){
            System.out.println(NONE);
            return;
        }
        String message = String.format(DAILY_DISCOUNT,numberFormat.format(discount));
        System.out.println(message);
    }
}
