package christmas.constant.message;

public enum ErrorMessage {
    ERROR("[ERROR]"),
    OVER_DATE_RANGE_ERROR(ERROR.message + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INCORRECT_INPUT_ERROR(ERROR.message + "숫자만 입력해야 합니다. 다시 입력해 주세요"),
    INCORRECT_ORDER_ERROR(ERROR.message + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    OVER_MAX_QUANTITY_ERROR(ERROR.message + "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
