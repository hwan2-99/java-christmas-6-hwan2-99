package christmas.validator;

import christmas.constant.Menu;
import christmas.constant.message.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderItemValidation {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;
    private final List<String> menus = (List<String>) Arrays.stream(Menu.values()).map(Menu::getName).toList();

    public Map<String, Integer> validateSeparatedOrders(String inputValue) {
        Map<String, Integer> orderInfo = separateOrders(inputValue);
        validateQuantity(orderInfo);
        validateMenuNames(orderInfo);
        validateMenuAllQuantity(orderInfo);
        validateDuplicateName(orderInfo);
        return orderInfo;
    }

    private Map<String, Integer> separateOrders(String inputValue) {
        try {
            return Arrays.stream(inputValue.split(COMMA)).map(orderItem -> orderItem.split(HYPHEN))
                    .filter(parts -> parts.length == 2)
                    .collect(Collectors.toMap(parts -> parts[0],
                            parts -> Integer.parseInt(parts[1]),
                            (existing, replacement) -> {
                                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
                            }));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
        }
    }

    private void validateQuantity(Map<String, Integer> orderInfo) {
        for (int quantity : orderInfo.values()) {
            if (quantity < MIN_QUANTITY) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }

    private void validateDuplicateName(Map<String, Integer> orderInfo) {
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

    private void validateMenuAllQuantity(Map<String, Integer> orderInfo) {
        int totalQuantity = orderInfo.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
        }
    }

    public void validateInputValue(String inputValue) {
        validateBlank(inputValue);
        validateIncorrectInputValues(inputValue);
    }

    private void validateBlank(String inputValue) {
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
        }
    }

    private void validateIncorrectInputValues(String inputValue) {
        List<String> orders = Arrays.stream(inputValue.split(","))
                .map(String::trim)
                .toList();
        for (String order : orders) {
            List<String> orderDetails = Arrays.stream(order.split("-"))
                    .map(String::trim)
                    .toList();

            if (orderDetails.size() != 2) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }
}
