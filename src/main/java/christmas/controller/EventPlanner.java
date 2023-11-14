package christmas.controller;

import christmas.model.Discount;
import christmas.model.EventBadge;
import christmas.model.EventManager;
import christmas.model.OrderItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlanner {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final EventManager eventManager;

    public EventPlanner() {
        this.eventManager = new EventManager();
    }

    public void run() {
        int visitDate = getVisitDate();
        OrderItem orderItem = getOrderDetails(visitDate);
        Discount discount = getDiscountDetail(visitDate, orderItem);
        getBadge(discount);
    }

    private OrderItem getOrderDetails(int visitDate) {
        OrderItem orderItem = new OrderItem(getOrderInfo());
        outputView.outputDateBenefitsMessage(visitDate);
        outputView.outputOrderDetails(orderItem);
        return orderItem;
    }


    private Discount getDiscountDetail(int visitDate, OrderItem orderItem) {
        Discount discount = new Discount(visitDate, orderItem, eventManager);
        outputView.outputDiscountDetails(discount, eventManager.isWeekDay(visitDate));
        outputView.outputAllDiscountPrice(discount);
        outputView.outputExpectedPrice(orderItem.calculateOrderPrice() + discount.calculateApplyDiscount());
        return discount;
    }

    private void getBadge(Discount discount) {
        EventBadge eventBadge = new EventBadge(discount);
        outputView.outputEventBadge(eventBadge);
    }

    private int getVisitDate() {
        return inputView.askVisitDate();
    }

    private Map<String, Integer> getOrderInfo() {
        return inputView.askOrders();
    }

}


