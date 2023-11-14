package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekdayEventStrategy implements EventStrategy {
    private static final int WEEK_DAY_DISCOUNT = 2023;

    private final LocalDate date;
    private final Integer quantity;

    private WeekdayEventStrategy(final Integer day, final Integer quantity) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.quantity = quantity;
    }

    public static WeekdayEventStrategy of(final Integer day, final Integer quantity) {
        return new WeekdayEventStrategy(day, quantity);
    }

    @Override
    public Integer itemDiscount() {
        if (!isEventDate()) {
            return ZERO;
        }
        return WEEK_DAY_DISCOUNT;
    }

    @Override
    public EventType getEventType() {
        return EventType.WEEKDAY;
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

    public int calculateDiscount() {
        if (isEventDate()) {
            return itemDiscount() * this.quantity;
        }
        return ZERO;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
