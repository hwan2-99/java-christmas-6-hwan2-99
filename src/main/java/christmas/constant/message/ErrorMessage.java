package christmas.constant.message;

public enum ErrorMessage {
    ERROR("[ERROR]"),
    OVER_DATE_RANGE_ERROR(ERROR.message + " 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INCORRECT_ORDER_ERROR(ERROR.message + " 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
