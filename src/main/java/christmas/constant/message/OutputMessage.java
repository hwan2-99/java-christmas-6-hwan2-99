package christmas.constant.message;

public enum OutputMessage {
    PRINT_EVENT_BENEFITS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_LIST("<주문 메뉴>"),
    ORDER_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    BONUS_MENU("<증정 메뉴>"),
    DISCOUNT_LIST("<혜택 내역>"),
    ALL_DISCOUNT_PRICE("<총혜택 금액>"),
    EXPECTED_PRICE("<할인 후 예상 결제 금액>"),
    DAILY_DISCOUNT("크리스마스 디데이 할인: %s원"),
    WEEK_DAY_DISCOUNT("평일 할인: %s원"),
    WEEKEND_DISCOUNT("주말 할인: %s원"),
    SPECIAL_DISCOUNT("특별 할인: %s원"),
    BONUS_MENU_DISCOUNT("증정 이벤트: %s원");
    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
