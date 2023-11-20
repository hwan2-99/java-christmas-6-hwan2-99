package christmas.constant;

public enum Date {
    FIRST_DAY(1),
    LAST_DAY(31);
    private final int date;

    Date(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
