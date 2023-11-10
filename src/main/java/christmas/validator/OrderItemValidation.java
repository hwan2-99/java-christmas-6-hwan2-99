package christmas.validator;

import christmas.constant.Menu;
import christmas.constant.message.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderItemValidation {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;
    private final List<String> menus = (List<String>) Arrays.stream(Menu.values()).map(Menu::getName).toList();

    public void validate(Map<String, Integer> orderInfo) {
        validateBlank(orderInfo);
        validateQuantity(orderInfo);
        validateMenuNames(orderInfo);
        validateMenuAllQuantity(orderInfo);
        validateDuplicateName(orderInfo);
    }

    private void validateQuantity(Map<String, Integer> orderInfo) {
        for (int quantity : orderInfo.values()) {
            if (quantity < MIN_QUANTITY) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }
    private void validateDuplicateName(Map<String, Integer> orderInfo){
        Set<String> uniqueNames = new HashSet<>();
        for (String orderName : orderInfo.keySet()) {
            if (!uniqueNames.add(orderName)) {
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
    private void validateBlank(Map<String, Integer> orderInfo) {
        for (String orderName : orderInfo.keySet()) {
            if (orderName.trim().isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }

    private void validateMenuAllQuantity(Map<String, Integer> orderInfo) {
        int totalQuantity = orderInfo.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
        }
    }
}
