package christmas.controller;

import christmas.view.InputView;

public class EventPlanner {
    private final InputView inputView = new InputView();
    public void run(){
        System.out.println(getVisitDate());
    }
    private int getVisitDate(){
        return inputView.askVisitDate();
    }
}
