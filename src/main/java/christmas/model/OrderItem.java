package christmas.model;

import christmas.constant.message.ErrorMessage;
import java.util.Map;

public class OrderItem {
    private static final int MIN_QUANTITY = 1;
    private final Map<String,Integer> orderInfo;


    public OrderItem(Map<String, Integer> orderInfo) {
        validate(orderInfo);
        this.orderInfo = orderInfo;
    }

    public Map<String, Integer> getOrderInfo() {
        return orderInfo;
    }
    private void validate(Map<String, Integer> orderInfo){
        validateQuantity(orderInfo);
    }
    private void validateQuantity(Map<String, Integer>orderInfo){
        for(int quantity : orderInfo.values()){
            if(quantity < MIN_QUANTITY){
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_ORDER_ERROR.getMessage());
            }
        }
    }

}
