package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.OrderItem;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OrderItemTest {
    InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @DisplayName("정해진 양식을 입력하면 메뉴와 수량이 제대로 입력된다")
    @Test
    void correctOrderItem() {
        consoleInput("타파스-1,초코케이크-3");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("타파스")).isEqualTo(1);
        assertThat(orderItem.getOrderInfo().get("초코케이크")).isEqualTo(3);
    }

    @DisplayName("정해진 양식을 제대로 입력하지 않으면 재입력을 한다")
    @Test
    void incorrectOrderItem() {
        consoleInput("타파스-1,초코케이크-ㅁ", "티본스테이크-2,레드와인-2");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("티본스테이크")).isEqualTo(2);
        assertThat(orderItem.getOrderInfo().get("레드와인")).isEqualTo(2);
    }

    @DisplayName("메뉴사이에 공백이 있으면 재입력을 한다")
    @Test
    void blankWithOrderItem() {
        consoleInput("타파스-1, ,초코케이크-2", "티본스테이크-2,레드와인-2");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("티본스테이크")).isEqualTo(2);
        assertThat(orderItem.getOrderInfo().get("레드와인")).isEqualTo(2);
    }

    @DisplayName("주문 메뉴는 20개로 제한한다.")
    @Test
    void OrderItemOverMaxAmount() {
        consoleInput("타파스-11,초코케이크-10", "티본스테이크-2,레드와인-2");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("티본스테이크")).isEqualTo(2);
        assertThat(orderItem.getOrderInfo().get("레드와인")).isEqualTo(2);
    }

    @DisplayName("메뉴 이름이 중복되게 주문할 수 없다.")
    @Test
    void OrderItemDuplicate() {
        consoleInput("타파스-1,타파스-10", "티본스테이크-2,레드와인-2");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("티본스테이크")).isEqualTo(2);
        assertThat(orderItem.getOrderInfo().get("레드와인")).isEqualTo(2);
    }

    @DisplayName("메뉴 등록이 안된 메뉴는 주문할 수 없다")
    @Test
    void OrderItemNoNames() {
        consoleInput("타파스-1,화이트와인-2", "티본스테이크-2,레드와인-2");
        OrderItem orderItem = new OrderItem(inputView.askOrders());

        assertThat(orderItem.getOrderInfo().get("티본스테이크")).isEqualTo(2);
        assertThat(orderItem.getOrderInfo().get("레드와인")).isEqualTo(2);
    }

    private void consoleInput(final String... args) {
        final byte[] buffer = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buffer));
    }
}
