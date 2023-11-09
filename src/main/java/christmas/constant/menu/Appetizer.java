package christmas.constant.menu;

public enum Appetizer {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("탸파스", 5500),
    SALAD("시저샐러드", 8000);
    private final String name;
    private final int price;

    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


}
