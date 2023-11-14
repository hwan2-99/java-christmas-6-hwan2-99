package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Discount;
import christmas.model.EventBadge;
import christmas.model.EventManager;
import christmas.model.OrderItem;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventBadgeTest {

    InputView inputView;
    EventManager eventManager;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        eventManager = new EventManager();
    }

    @AfterEach
    void consoleClear() {
        Console.close();
    }

    @ParameterizedTest
    @DisplayName("총 혜택금액이 30000원 이상일 경우 산타뱃지를 받는다")
    @ValueSource(ints = 3)
    void santaTest(int visitDate) {
        consoleInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate, orderItem, eventManager);
        EventBadge eventBadge = new EventBadge(discount);

        assertThat(eventBadge.getBadgeName()).isEqualTo("산타");
    }
    @ParameterizedTest
    @DisplayName("총 혜택금액이 10000원 이상 30000원 미만일 경우 트리뱃지를 받는다")
    @ValueSource(ints = 3)
    void treeTest(int visitDate) {
        consoleInput("초코케이크-6,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate, orderItem, eventManager);
        EventBadge eventBadge = new EventBadge(discount);

        assertThat(eventBadge.getBadgeName()).isEqualTo("트리");
    }

    @ParameterizedTest
    @DisplayName("총 혜택금액이 5000원 이상 10000원 미만일 경우 별뱃지를 받는다")
    @ValueSource(ints = 3)
    void starTest(int visitDate) {
        consoleInput("초코케이크-2,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate, orderItem, eventManager);
        EventBadge eventBadge = new EventBadge(discount);

        assertThat(eventBadge.getBadgeName()).isEqualTo("별");
    }
    @ParameterizedTest
    @DisplayName("총 혜택금액이 5000원 미만일 경우 뱃지는 받지못한다")
    @ValueSource(ints = 3)
    void noneTest(int visitDate) {
        consoleInput("초코케이크-1,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate, orderItem, eventManager);
        EventBadge eventBadge = new EventBadge(discount);

        assertThat(eventBadge.getBadgeName()).isEqualTo("없음");
    }
    private void consoleInput(final String... args) {
        final byte[] buffer = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buffer));
    }
}
