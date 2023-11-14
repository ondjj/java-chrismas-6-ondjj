package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.ZERO;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentEventStrategy implements EventStrategy {
    private static final int PRESENT_DISCOUNT = 25000;
    private static final int PRESENT_RANGE = 120000;

    private final Integer price;

    private PresentEventStrategy(final Integer price) {
        this.price = price;
    }

    public static PresentEventStrategy of(final Integer price) {
        return new PresentEventStrategy(price);
    }

    @Override
    public Integer itemDiscount() {
        if (!isPresentRange()) {
            return ZERO;
        }
        return PRESENT_DISCOUNT;
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

    private void putNone(final EventType eventType, final Map<String, String> details) {
        details.put(eventType.getNone(), eventType.getNone());
    }

    private void putDetails(final EventType eventType, final Integer discount,
                                   final Map<String, String> details) {
        details.put(eventType.getDescription(), DASH + Parser.decimalFormatter(discount));
    }

    public String getPresent() {
        if (isPresentRange()) {
            return getEventType().getPresentDescription();
        }
        return getEventType().getNone();
    }

    private boolean isValid(final Integer discount) {
        return discount > ZERO;
    }

    private boolean isPresentRange() {
        return price >= PRESENT_RANGE;
    }
}
