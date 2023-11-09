package christmas.view;

public class OutputView {
    private static final String PRINT_EVENT_BENEFITS = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public void outputDateBenefitsMessage(int visitDate){
        String message = String.format(PRINT_EVENT_BENEFITS,visitDate);

        System.out.println(message);
    }
}
