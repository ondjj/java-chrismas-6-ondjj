package christmas.model.event;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class WeekendEventStrategy extends EventDetailsHandler<WeekendEventStrategy> {
    private static final int WEEK_END_DISCOUNT = 2_023;

    private final LocalDate date;
    private final Integer quantity;

    private WeekendEventStrategy(final Integer day, final Integer quantity) {
        super(EventType.WEEKEND);
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.quantity = quantity;
    }

    public static WeekendEventStrategy of(final Integer day, final Integer quantity) {
        return new WeekendEventStrategy(day, quantity);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return calculateDiscount();
        }
        return ZERO;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        return createEventDetails(getEventType(), itemDiscount());
    }

    private int calculateDiscount() {
        return WEEK_END_DISCOUNT * this.quantity;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
