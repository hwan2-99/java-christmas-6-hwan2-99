package christmas.controller;

import christmas.constant.Price;
import christmas.model.Discount;
import christmas.model.EventManager;
import christmas.model.OrderItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Map.Entry;

public class EventPlanner {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        int visitDate = getVisitDate();
        OrderItem orderItem = new OrderItem(getOrderInfo());
        outputOrderDetails(visitDate, orderItem);
        initDiscount(visitDate,orderItem);
    }

    private void outputOrderDetails(int visitDate, OrderItem orderItem) {
        outputView.outputDateBenefitsMessage(visitDate);
        outputView.outputOrderMenus(orderItem);
        outputView.outputOrderPrice(orderItem.getOrderPrice());
        outputView.outputBonusMenu(orderItem.overEventPrice());
    }

    private void initDiscount(int visitDate, OrderItem orderItem) {
        EventManager eventManager = new EventManager();
        Discount discount = new Discount(orderItem);

        int dailyDiscount = discount.dailyDiscount(eventManager.getCalender().get(visitDate));
        int weekDiscount = discount.weekDiscount(eventManager.isWeekDay(visitDate));
        int specialDiscount = discount.specialDiscount(eventManager.isSpecialDay(visitDate));
        int bonusMenuDiscount = discount.bonusMenuDiscount(orderItem.overEventPrice());

        outputView.outputDiscountDetails(dailyDiscount, weekDiscount, specialDiscount, eventManager.isWeekDay(visitDate),bonusMenuDiscount);
    }

    private int getVisitDate() {
        return inputView.askVisitDate();
    }

    private Map<String, Integer> getOrderInfo() {
        return inputView.askOrderMenus();
    }

}


