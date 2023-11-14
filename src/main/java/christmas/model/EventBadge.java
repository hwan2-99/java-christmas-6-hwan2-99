package christmas.model;

import christmas.constant.Badge;

public class EventBadge {
    private static final String NONE = "없음";
    private final Discount discount;

    public EventBadge(Discount discount) {
        this.discount = discount;
    }

    public String getBadgeName() {
        return judgementBadge();
    }

    private String judgementBadge() {
        if (isStar()) {
            return Badge.STAR.getName();
        }
        if (isTree()) {
            return Badge.TREE.getName();
        }
        if (isSanta()) {
            return Badge.SANTA.getName();
        }
        return NONE;
    }

    private boolean isStar() {
        int judgementPrice = Math.abs(discount.calculateDiscountPrice());
        return Badge.STAR.getPrice() <= judgementPrice && judgementPrice < Badge.TREE.getPrice();
    }

    private boolean isTree() {
        int judgementPrice = Math.abs(discount.calculateDiscountPrice());
        return Badge.TREE.getPrice() <= judgementPrice && judgementPrice < Badge.SANTA.getPrice();
    }

    private boolean isSanta() {
        int judgementPrice = Math.abs(discount.calculateDiscountPrice());
        return Badge.SANTA.getPrice() <= judgementPrice;
    }
}
