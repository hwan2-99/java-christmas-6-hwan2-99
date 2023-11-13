package christmas.domain;
import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Discount;
import christmas.model.EventManager;
import christmas.model.OrderItem;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DiscountTest {
    InputView inputView;
    EventManager eventManager;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        eventManager = new EventManager();
    }

    @AfterEach
    void closeConsole() {
        Console.close();
    }
    @ParameterizedTest
    @DisplayName("모든 이벤트 조건을 만족하면 정상적인 이벤트 혜택을 받을 수 있다.")
    @ValueSource(ints = 3)
    void discount(int visitDate){
        consoleInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate,orderItem,eventManager);

        assertThat(discount.dailyDiscount()).isEqualTo(-1200);
        assertThat(discount.weekDiscount()).isEqualTo(-4046);
        assertThat(discount.calculateDiscountPrice()).isEqualTo(-31246);
    }

    @ParameterizedTest
    @DisplayName("할인 전 주문금액이 10000원보다 낮으면 이벤트 기간중이어도 혜택을 받을 수 없다.")
    @ValueSource(ints = 3)
    void NoApplyDiscount(int visitDate){
        consoleInput("타파스-1,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate,orderItem,eventManager);

        assertThat(discount.dailyDiscount()).isEqualTo(0);
        assertThat(discount.weekDiscount()).isEqualTo(0);
        assertThat(discount.calculateDiscountPrice()).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("크리스마스 디데이할인은 25일 까지만 적용된다")
    @ValueSource(ints = 26)
    void dailyEventEndDiscount(int visitDate){
        consoleInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate,orderItem,eventManager);

        assertThat(discount.dailyDiscount()).isEqualTo(0);
        assertThat(discount.weekDiscount()).isEqualTo(-4046);
        assertThat(discount.calculateDiscountPrice()).isEqualTo(-29046);
    }

    @ParameterizedTest
    @DisplayName("음료수만 주문할 경우 할인 이벤트는 적용되지 않는다.")
    @ValueSource(ints = 4)
    void onlyBeverage(int visitDate){
        consoleInput("레드와인-4,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate,orderItem,eventManager);

        assertThat(discount.dailyDiscount()).isEqualTo(0);
        assertThat(discount.weekDiscount()).isEqualTo(0);
        assertThat(discount.calculateDiscountPrice()).isEqualTo(0);
    }
    @ParameterizedTest
    @DisplayName("할인전 주문 금액이 120000원 이상일 경우 증정메뉴인 샴페인을 준다.")
    @ValueSource(ints = 4)
    void overEventPrice(int visitDate){
        consoleInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderItem orderItem = new OrderItem(inputView.askOrders());
        Discount discount = new Discount(visitDate,orderItem,eventManager);

        assertThat(discount.bonusMenuDiscount()).isEqualTo(-25000);
    }

    private void consoleInput(final String... args) {
        final byte[] buffer = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buffer));
    }
}
