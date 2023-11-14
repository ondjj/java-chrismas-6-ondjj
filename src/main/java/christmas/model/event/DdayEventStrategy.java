package christmas.model.event;

import static christmas.util.Constants.*;

import christmas.util.enums.EventType;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DdayEventStrategy extends EventDetailsHandler implements EventStrategy {
    private static final int D_DAY_DISCOUNT = 900;
    private static final int CHRISTMAS = 25;
    private static final int INCREMENT = 100;

    private final LocalDate date;
    private final Integer totalPrice;

    private DdayEventStrategy(final Integer day, final Integer totalPrice) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.totalPrice = totalPrice;
    }

    public static DdayEventStrategy of(final Integer day, final Integer totalPrice) {
        return new DdayEventStrategy(day, totalPrice);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return calculateDiscount();
        }
        return ZERO;
    }

    @Override
    public EventType getEventType() {
        return EventType.D_DAY;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> details = new LinkedHashMap<>();
        if (isEventDate()) {
            return createEventDetails(getEventType(), itemDiscount());
        }
        details.put(getEventType().getNone(), getEventType().getNone());
        return details;
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

    private int calculateDiscount() {
        return D_DAY_DISCOUNT + (date.getDayOfMonth() * INCREMENT);
    }

    private boolean isEventDate() {
        return totalPrice >= LIMIT_RANGE && date.getDayOfMonth() <= CHRISTMAS;
    }
}
