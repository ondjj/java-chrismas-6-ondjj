package christmas.model;

import christmas.util.enums.Badge;

public class EventBadge {
    private static final String NONE = "없음";
    private static final Integer EVENT_RANGE = 10000;

    private final Integer totalPrice;

    private EventBadge(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static EventBadge of(Integer totalPrice) {
        return new EventBadge(totalPrice);
    }

    public String determineBadge() {
        if (!isValidEvent()) {
            return NONE;
        }
        Badge badge = Badge.getBadgeByAmount(this.totalPrice);
        return badge.getBadgeName();
    }

    private boolean isValidEvent() {
        return this.totalPrice >= EVENT_RANGE;
    }
}
