package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekendEvent implements Event {
    private static final int WEEK_END_DISCOUNT = 2023;

    private final LocalDate date;
    private final Integer quantity;

    private WeekendEvent(final Integer day, final Integer quantity) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.quantity = quantity;
    }

    public static WeekendEvent of(final Integer day, final Integer quantity) {
        return new WeekendEvent(day, quantity);
    }

    @Override
    public Integer itemDiscount() {
        return WEEK_END_DISCOUNT;
    }

    @Override
    public EventType getEventType() {
        return EventType.WEEKEND;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> details = new LinkedHashMap<>();
        if (isEventDate()) {
            return getDetailEvent(details);
        }
        details.put(getEventType().getNone(), getEventType().getNone());
        return details;
    }

    private Map<String, String> getDetailEvent(final Map<String, String> details) {
        details.put(getEventType().getDescription(), DASH + Parser.decimalFormatter(getWon()));
        return details;
    }

    public int getWon() {
        if (isEventDate()) {
            return itemDiscount() * this.quantity;
        }
        return 0;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
