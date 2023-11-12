package christmas.domain;
import static org.assertj.core.api.Assertions.*;

import christmas.model.EventManager;
import org.junit.jupiter.api.DisplayName;
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
}
