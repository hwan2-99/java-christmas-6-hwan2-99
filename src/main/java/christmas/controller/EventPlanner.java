package christmas.controller;

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
        outputView.outputDateBenefitsMessage(visitDate);
        outputView.outputOrderMenus(orderItem);
        outputView.outputOrderPrice(orderItem.getOrderPrice());
        outputView.outputBonusMenu(orderItem.priceOverEventPrice());
        EventManager eventManager = new EventManager();
        Discount discount = new Discount(orderItem);
        outputWeekDiscountList(discount.weekDiscount(eventManager.isWeekDay(visitDate)),eventManager.isWeekDay(visitDate));
        outputView.outputSpecialDiscountList(discount.specialDiscount(eventManager.isSpecialDay(visitDate)));
    }

    private int getVisitDate() {
        return inputView.askVisitDate();
    }

    private Map<String, Integer> getOrderInfo() {
        return inputView.askOrderMenus();
    }
    private void outputWeekDiscountList(int discount,boolean isWeekDay){
        if (isWeekDay){
            outputView.outputWeekDayDiscountList(discount);
            return;
        }
        outputView.outputWeekendDiscountList(discount);
    }
}
