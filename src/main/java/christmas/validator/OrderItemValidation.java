package christmas.validator;

import christmas.constant.Menu;
import christmas.constant.message.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderItemValidation {
    private static final int MIN_QUANTITY = 1;
    private final List<String> menus = (List<String>) Arrays.stream(Menu.values()).map(Menu::getName).toList();

    public void validate(Map<String, Integer> orderInfo) {
        validateQuantity(orderInfo);
        validateMenuNames(orderInfo);
    }

    private void validateQuantity(Map<String, Integer> orderInfo) {
        for (int quantity : orderInfo.values()) {
            if (quantity < MIN_QUANTITY) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }

    private void validateMenuNames(Map<String, Integer> orderInfo) {
        for (String orderName : orderInfo.keySet()) {
            if (!menus.contains(orderName)) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }
}