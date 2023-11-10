package christmas.constant;

public enum Price {
    CHECK_CHAMPAGNE_GIVE(120000);
    private final int price;

    Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
