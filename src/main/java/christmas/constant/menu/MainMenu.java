package christmas.constant.menu;

public enum MainMenu {
    STEAK("티스테이크", 55000),
    BARBECUE("바비큐립", 54000),
    SEAFOOD_SPAGHETTI("해산물파스타", 35000),
    CHRISTMAS_SPAGHETTI("크리스마스파스타", 25000);
    private final String name;
    private final int price;

    MainMenu(String name, int price) {
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
