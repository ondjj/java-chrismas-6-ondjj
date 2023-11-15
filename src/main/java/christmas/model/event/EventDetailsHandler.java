package christmas.model.event;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.NONE;
import static christmas.util.Constants.ZERO;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventDetailsHandler<T> implements EventStrategy{
    private final EventType eventType;

    public EventDetailsHandler(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public Integer itemDiscount() {
        return ZERO;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> noneEvent = new HashMap<>();
        noneEvent.put(NONE, NONE);
        return noneEvent;
    }

    @Override
    public Map<String, String> createEventDetails(final EventType eventType, final Integer discount) {
        Map<String, String> details = new LinkedHashMap<>();
        if (isValidDiscount(discount)) {
            putDetails(eventType, discount, details);
            return details;
        }
        putNone(eventType, details);
        return details;
    }

    private void putNone(EventType eventType, Map<String, String> details) {
        details.put(eventType.getNone(), eventType.getNone());
    }

    private void putDetails(EventType eventType, Integer discount, Map<String, String> details) {
        details.put(eventType.getDescription(), DASH + Parser.decimalFormatter(discount));
    }

    private boolean isValidDiscount(final Integer discount) {
        return discount > ZERO;
    }
}
