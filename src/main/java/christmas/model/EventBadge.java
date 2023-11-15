package christmas.model;

import static christmas.util.Constants.NONE;

import christmas.util.enums.Badge;

public class EventBadge {
    private static final Integer EVENT_RANGE = 5_000;

    private final Integer actualBenefit;

    private EventBadge(Integer actualBenefit) {
        this.actualBenefit = actualBenefit;
    }

    public static EventBadge of(Integer totalPrice) {
        return new EventBadge(totalPrice);
    }

    public String determineBadge() {
        if (!isValidEvent()) {
            return NONE;
        }
        Badge badge = Badge.getBadgeByAmount(this.actualBenefit);
        return badge.getBadgeName();
    }

    private boolean isValidEvent() {
        return this.actualBenefit >= EVENT_RANGE;
    }
}
