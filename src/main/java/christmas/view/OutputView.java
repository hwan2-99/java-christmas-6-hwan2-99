package christmas.view;

import christmas.model.OrderItem;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String PRINT_EVENT_BENEFITS = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_LIST = "<주문 메뉴>";
    private static final String COUNT = "개";
    public void outputDateBenefitsMessage(int visitDate){
        String message = String.format(PRINT_EVENT_BENEFITS,visitDate);

        System.out.println(message);
    }

    public void outputOrderMenus(OrderItem orderItem){
        System.out.println("\n"+ORDER_LIST);
        for (Entry<String, Integer> entry : orderItem.getOrderInfo().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue()+COUNT);
        }
    }
}