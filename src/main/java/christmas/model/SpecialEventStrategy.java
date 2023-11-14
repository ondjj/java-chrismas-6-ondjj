package christmas.model;

import static christmas.util.Constants.*;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpecialEventStrategy implements EventStrategy {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int CHRISTMAS = 25;

    private final LocalDate date;
    private final Integer totalPrice;

    private SpecialEventStrategy(final Integer day, final Integer totalPrice) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.totalPrice = totalPrice;
    }

    public static SpecialEventStrategy of(final Integer day, final Integer totalPrice) {
        return new SpecialEventStrategy(day, totalPrice);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return SPECIAL_DISCOUNT;
        }
        return ZERO;
    }

    @Override
    public EventType getEventType() {
        return EventType.SPECIAL;
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

    private boolean isValid(final Integer discount) {
        return discount > ZERO;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return totalPrice >= LIMIT_RANGE && (dayOfWeek == DayOfWeek.SUNDAY) || (date.getDayOfMonth() == CHRISTMAS);
    }
}
