package christmas.model;

import static christmas.util.Constants.*;

import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class SpecialEventStrategy implements EventStrategy {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int CHRISTMAS = 25;

    private final LocalDate date;

    private SpecialEventStrategy(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static SpecialEventStrategy of(final Integer day) {
        return new SpecialEventStrategy(day);
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
        return EventUtils.createEventDetails(getEventType(), itemDiscount());
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SUNDAY) || (date.getDayOfMonth() == CHRISTMAS);
    }
}
