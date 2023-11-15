package christmas.model.event;

import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentEventStrategy extends EventDetailsHandler implements EventStrategy {
    private static final int PRESENT_DISCOUNT = 25_000;
    private static final int PRESENT_RANGE = 120_000;

    private final Integer price;

    private PresentEventStrategy(final Integer price) {
        this.price = price;
    }

    public static PresentEventStrategy from(final Integer price) {
        return new PresentEventStrategy(price);
    }

    @Override
    public Integer itemDiscount() {
        if (isPresentRange()) {
            return PRESENT_DISCOUNT;
        }
        return ZERO;
    }

    @Override
    public EventType getEventType() {
        return EventType.PRESENT;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        return createEventDetails(getEventType(), itemDiscount());
    }

    @Override
    public Map<String, String> createEventDetails(final EventType eventType, final Integer discount) {
        Map<String, String> details = new LinkedHashMap<>();
        if (isValid(discount)) {
            putDetails(eventType, discount, details);
            return details;
        }
        putNone(eventType, details);
        return details;
    }

    public String getPresent() {
        if (isPresentRange()) {
            return getEventType().getPresentDescription();
        }
        return getEventType().getNone();
    }

    private boolean isPresentRange() {
        return price >= PRESENT_RANGE;
    }
}
