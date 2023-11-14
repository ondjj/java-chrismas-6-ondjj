package christmas.model.event;

import static christmas.util.Constants.*;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.util.Map;

public abstract class EventDetailsHandler {
    protected void putNone(EventType eventType, Map<String, String> details) {
        details.put(eventType.getNone(), eventType.getNone());
    }

    protected void putDetails(EventType eventType, Integer discount, Map<String, String> details) {
        details.put(eventType.getDescription(), DASH + Parser.decimalFormatter(discount));
    }

    protected boolean isValid(final Integer discount) {
        return discount > ZERO;
    }
}
