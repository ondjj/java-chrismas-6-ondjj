package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DdayEventStrategy implements EventStrategy {
    private static final int D_DAY_DISCOUNT = 900;
    private static final int CHRISTMAS = 25;
    private static final int INCREMENT = 100;

    private final LocalDate date;

    private DdayEventStrategy(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static DdayEventStrategy of(final Integer day) {
        return new DdayEventStrategy(day);
    }

    @Override
    public Integer itemDiscount() {
        if (!isEventDate()) {
            return ZERO;
        }
        return getDiscount();
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

    private boolean isValid(final Integer discount) {
        return discount > ZERO;
    }

    private void putNone(final EventType eventType, final Map<String, String> details) {
        details.put(eventType.getNone(), eventType.getNone());
    }

    private void putDetails(final EventType eventType, final Integer discount,
                            final Map<String, String> details) {
        details.put(eventType.getDescription(), DASH + Parser.decimalFormatter(discount));
    }

    private int getDiscount() {
        return D_DAY_DISCOUNT + (date.getDayOfMonth() * INCREMENT);
    }

    private boolean isEventDate() {
        return date.getDayOfMonth() <= CHRISTMAS;
    }
}
