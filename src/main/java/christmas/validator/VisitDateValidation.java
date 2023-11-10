package christmas.validator;

import christmas.constant.Date;
import christmas.constant.message.ErrorMessage;

public class VisitDateValidation {
    public void validate(String inputValue){
        validateIncorrectInput(inputValue);
        validateDateRange(inputValue);
    }
    private void validateIncorrectInput(String inputValue){
        try {
            Integer.parseInt(inputValue);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.OVER_DATE_RANGE_ERROR.getMessage());
        }
    }
    private void validateDateRange(String inputValue){
        int date = Integer.parseInt(inputValue);
        if(date < Date.FIRST_DAY.getDate() || date > Date.LAST_DAY.getDate()){
            throw new IllegalArgumentException(ErrorMessage.OVER_DATE_RANGE_ERROR.getMessage());
        }
    }

}
