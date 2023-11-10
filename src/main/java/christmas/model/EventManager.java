package christmas.model;

import christmas.constant.Price;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static final int YEAR = 2023;
    private static final int DECEMBER = 12;
    private static final int FIRST_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private final Map<Integer, Integer> calender = new HashMap<>();


    public EventManager() {
        for (int i = FIRST_DAY; i <= CHRISTMAS_DAY; i++) {
            calender.put(i, Price.INITIAL_DISCOUNT_PRICE.getPrice() + Price.ADDITIONAL_DISCOUNT_PRICE.getPrice() * i);
        }
    }

    public Map<Integer, Integer> getCalender() {
        return calender;
    }

    public boolean isEndEvent(int visitDate) {
        return visitDate > CHRISTMAS_DAY;
    }

    public boolean isWeekDay(int visitDate) {
        LocalDate visitDay = LocalDate.of(YEAR, DECEMBER, visitDate);
        DayOfWeek dayOfWeek = visitDay.getDayOfWeek();

        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay(int visitDate) {
        LocalDate visitDay = LocalDate.of(YEAR, DECEMBER, visitDate);
        DayOfWeek dayOfWeek = visitDay.getDayOfWeek();

        return dayOfWeek == DayOfWeek.SUNDAY || visitDate == CHRISTMAS_DAY;
    }
}
