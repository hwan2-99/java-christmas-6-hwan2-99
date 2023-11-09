package christmas.validator;

public class VisitDateValidation {
    public void validate(String inputValue){
        validateIncorrectInput(inputValue);
    }
    private void validateIncorrectInput(String inputValue){
        try {
            Integer.parseInt(inputValue);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }
    }

}
