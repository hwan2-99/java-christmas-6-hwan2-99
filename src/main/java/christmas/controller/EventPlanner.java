package christmas.controller;

import christmas.model.Discount;
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
        OrderItem orderItem = new OrderItem(getOrderInfo());
        getOrderDetails(visitDate, orderItem);
        getBadge(visitDate, orderItem);
    }

    private void getOrderDetails(int visitDate, OrderItem orderItem) {
        outputView.outputDateBenefitsMessage(visitDate);
        outputView.outputOrderMenus(orderItem);
        outputView.outputOrderPrice(orderItem.calculateOrderPrice());
        outputView.outputBonusMenu(orderItem.isOverEventPrice());
        outputView.outputExpectedPrice(orderItem.calculateOrderPrice(), initDiscount(visitDate, orderItem));
    }

    private int initDiscount(int visitDate, OrderItem orderItem) {
        Discount discount = new Discount(orderItem, eventManager);

        int dailyDiscount = discount.dailyDiscount(visitDate);
        int weekDiscount = discount.weekDiscount(visitDate);
        int specialDiscount = discount.specialDiscount(visitDate);
        int bonusMenuDiscount = discount.bonusMenuDiscount();

        outputView.outputDiscountDetails(dailyDiscount, weekDiscount, specialDiscount,
                eventManager.isWeekDay(visitDate), bonusMenuDiscount);
        outputView.outputAllDiscountPrice(discount.calculateDiscountPrice(visitDate));

        return dailyDiscount + weekDiscount + specialDiscount;
    }

    private void getBadge(int visitDate, OrderItem orderItem) {
        Discount discount = new Discount(orderItem, eventManager);

        outputView.outputEventBadge(discount.calculateDiscountPrice(visitDate));
    }

    private int getVisitDate() {
        return inputView.askVisitDate();
    }

    private Map<String, Integer> getOrderInfo() {
        return inputView.askOrderMenus();
    }

}


