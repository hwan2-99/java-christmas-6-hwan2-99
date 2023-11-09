package christmas.controller;

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
        for (Entry<String, Integer> entry : orderItem.getOrderInfo().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        outputView.outputDateBenefitsMessage(visitDate);
    }

    private int getVisitDate() {
        return inputView.askVisitDate();
    }

    private Map<String, Integer> getOrderInfo() {
        return inputView.askOrderMenus();
    }
}
