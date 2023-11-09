package christmas.constant;

public enum Menu {
    STEAK("티스테이크", "main", 55000),
    BARBECUE("바비큐립", "main", 54000),
    SEAFOOD_SPAGHETTI("해산물파스타", "main", 35000),
    CHRISTMAS_SPAGHETTI("크리스마스파스타", "main", 25000),
    MUSHROOM_SOUP("양송이수프", "appetizer", 6000),
    TAPAS("탸파스", "appetizer", 5500),
    SALAD("시저샐러드", "appetizer", 8000),
    OKE("제로콜라", "beverage", 3000),
    WINE("레드와인", "beverage", 60000),
    CHAMPAGNE("샴페인", "beverage", 25000),
    CAKE("초코케이크", "dessert", 15000),
    ICE_CREAM("아이스크림", "dessert", 5000);
    private final String name;
    private final String type;
    private final int price;

    Menu(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

}
