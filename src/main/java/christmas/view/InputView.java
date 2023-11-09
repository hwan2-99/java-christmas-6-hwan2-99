package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.message.ErrorMessage;
import christmas.model.OrderItem;
import christmas.validator.OrderItemValidation;
import christmas.validator.VisitDateValidation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.print.DocFlavor.STRING;

public class InputView {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final String ASK_VISIT_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_INFO = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final VisitDateValidation visitDateValidation = new VisitDateValidation();
    private final OrderItemValidation orderItemValidation = new OrderItemValidation();

    public int askVisitDate() {
        System.out.println(ASK_VISIT_DATE_MESSAGE);
        while (true) {
            try {
                String inputValue = Console.readLine();
                visitDateValidation.validate(inputValue);
                return Integer.parseInt(inputValue);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Map<String, Integer> askOrderMenus() {
        System.out.println(ASK_ORDER_INFO);
        while (true) {
            try {
                Map<String, Integer> orderInfo = inputOrders();
                orderItemValidation.validate(orderInfo);
                return orderInfo;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<String, Integer> inputOrders() {
        return Arrays.stream(Console.readLine().split(COMMA))
                .map(orderItem -> orderItem.split(HYPHEN))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> Integer.parseInt(parts[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
                        }
                ));
    }
}
