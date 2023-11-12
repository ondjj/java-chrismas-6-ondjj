package christmas.model;

import static christmas.util.Constants.ZERO;

import christmas.util.EventUtils;
import christmas.util.enums.EventType;
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
        if (!isPresentRange()){
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
        return EventUtils.createEventDetails(getEventType(), itemDiscount());
    }

    public String getPresent() {
        if (isPresentRange()){
            return getEventType().getPresentDescription();
        }
        return getEventType().getNone();
    }

    private boolean isPresentRange() {
        return price >= PRESENT_RANGE;
    }
}
