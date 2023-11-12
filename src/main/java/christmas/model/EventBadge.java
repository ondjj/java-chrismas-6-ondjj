package christmas.model;

import christmas.util.enums.Badge;

public class EventBadge {
    private final Integer totalPrice;

    private EventBadge(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static EventBadge of(Integer totalPrice) {
        return new EventBadge(totalPrice);
    }

    public String determineBadge() {
        if (!isValidEvent()) {
            return "없음";
        }
        Badge badge = Badge.getBadgeByAmount(this.totalPrice);
        return badge.getBadgeName();
    }

    private boolean isValidEvent() {
        return this.totalPrice >= 10000;
    }
}
