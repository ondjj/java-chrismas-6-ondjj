package christmas.model.event;

import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.util.Map;

public class PresentEventStrategy extends BaseEventHandler<PresentEventStrategy> {
    private static final int PRESENT_DISCOUNT = 25_000;
    private static final int PRESENT_RANGE = 120_000;

    private final Integer price;

    private PresentEventStrategy(final Integer price) {
        super(EventType.PRESENT);
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
    public Map<String, String> extractEventDetails() {
        return createEventDetails(getEventType(), itemDiscount());
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
