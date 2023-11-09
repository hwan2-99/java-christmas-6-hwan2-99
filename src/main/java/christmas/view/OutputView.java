package christmas.view;

public class OutputView {
    private static final String ASK_VISIT_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public void askVisitDate() {
        System.out.println(ASK_VISIT_DATE_MESSAGE);
    }
}
