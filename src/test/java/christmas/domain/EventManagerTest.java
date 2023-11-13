package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import christmas.model.EventManager;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventManagerTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("12월 1일과 12월 2일은 주말이다")
    void weekendTest(int visitDate) {
        EventManager eventManager = new EventManager();
        boolean isWeekDay = eventManager.isWeekDay(visitDate);
        assertThat(isWeekDay).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 26})
    @DisplayName("12월 25일과 12월 26일은 평일이다")
    void weekDayTest(int visitDate) {
        EventManager eventManager = new EventManager();
        boolean isWeekDay = eventManager.isWeekDay(visitDate);
        assertThat(isWeekDay).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 17})
    @DisplayName("크리스마스와 12월 17일은 특별할인 하는날이다")
    void specialDayTest(int visitDate) {
        EventManager eventManager = new EventManager();
        boolean isSpecialDay = eventManager.isSpecialDay(visitDate);
        assertThat(isSpecialDay).isTrue();
    }

        @Test
        @DisplayName("크리스마스 디데이 할인은 1일부터 25일까지만 적용되고 하루에 100원씩 늘어난다.")
        void dailyDiscount() {
        EventManager eventManager = new EventManager();
        Map<Integer, Integer> calender = eventManager.getCalender();

        assertThat(calender.get(3)).isEqualTo(1200);
        assertThat(calender.get(25)).isEqualTo(3400);
        assertThat(calender.get(26)).isEqualTo(0);
    }
}
