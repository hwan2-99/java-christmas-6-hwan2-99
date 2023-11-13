package christmas.constant;

public enum Price {
    CHECK_CHAMPAGNE_GIVE(120000),
    INITIAL_DISCOUNT(1000),
    ADDITIONAL_DISCOUNT(100),
    DAILY_DISCOUNT(2023),
    NONE(0),
    EVENT_APPLY_PRICE(10000);
    private final int price;

    Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
