package christmas.controller;

import christmas.view.InputView;
import java.util.Map;
import java.util.Map.Entry;

public class EventPlanner {
    private final InputView inputView = new InputView();
    public void run(){
        System.out.println(getVisitDate());
        Map<String,Integer> orderInfo = getOrderInfo();
        for (Entry<String, Integer> entry : orderInfo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    private int getVisitDate(){
        return inputView.askVisitDate();
    }
    private Map<String,Integer> getOrderInfo() {
        return inputView.askOrderMenus();
    }
}
