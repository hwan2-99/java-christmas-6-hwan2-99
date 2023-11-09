package christmas.controller;

import christmas.view.OutputView;

public class EventPlanner {
    private final OutputView outputView = new OutputView();
    public void run(){
        getVisitDate();
    }
    private void getVisitDate(){
        outputView.askVisitDate();
    }
}
