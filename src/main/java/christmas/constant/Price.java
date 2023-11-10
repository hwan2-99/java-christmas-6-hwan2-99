package christmas.constant;

public enum Price {
    CHECK_CHAMPAGNE_GIVE(120000),
    INITIAL_DISCOUNT_PRICE(1000),
    ADDITIONAL_DISCOUNT_PRICE(100),
    DAILY_DISCOUNT_PRICE(2023);
    private final int price;

    Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
