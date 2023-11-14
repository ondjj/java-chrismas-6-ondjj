package christmas.model;

import static christmas.util.Constants.*;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekendEventStrategy implements EventStrategy {
    private static final int WEEK_END_DISCOUNT = 2023;

    private final LocalDate date;
    private final Integer quantity;

    private WeekendEventStrategy(final Integer day, final Integer quantity) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.quantity = quantity;
    }

    public static WeekendEventStrategy of(final Integer day, final Integer quantity) {
        return new WeekendEventStrategy(day, quantity);
    }

    @Override
    public Integer itemDiscount() {
        if (!isEventDate()) {
            return ZERO;
        }
        return WEEK_END_DISCOUNT;
    }

    @Override
    public EventType getEventType() {
        return EventType.WEEKEND;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        return createEventDetails(getEventType(), calculateDiscount());
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

    private int calculateDiscount() {
        if (isEventDate()) {
            return itemDiscount() * this.quantity;
        }
        return ZERO;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
