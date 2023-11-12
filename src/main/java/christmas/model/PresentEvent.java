package christmas.model;

import static christmas.util.Constants.DASH;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentEvent implements Event {
    private static final int PRESENT_DISCOUNT = 25000;
    private static final int PRESENT_RANGE = 120000;

    private final Integer price;

    private PresentEvent(final Integer price) {
        this.price = price;
    }

    public static PresentEvent of(final Integer price) {
        return new PresentEvent(price);
    }

    @Override
    public Integer itemDiscount() {
        if (!isPresentRange()){
            return 0;
        }
        return PRESENT_DISCOUNT;
    }

    @Override
    public EventType getEventType() {
        return EventType.PRESENT;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> details = new LinkedHashMap<>();
        if (isPresentRange()) {
            return addEventDetails(details, itemDiscount());
        }
        details.put(getEventType().getNone(), getEventType().getNone());
        return details;
    }

    public String getPresent() {
        if (isPresentRange()){
            return getEventType().getPresentDescription();
        }
        return getEventType().getNone();
    }

    private Map<String,String> addEventDetails(Map<String, String> details, int discount) {
        details.put(getEventType().getDescription(), DASH + Parser.decimalFormatter(discount));
        return details;
    }

    private boolean isPresentRange() {
        return price >= PRESENT_RANGE;
    }
}
