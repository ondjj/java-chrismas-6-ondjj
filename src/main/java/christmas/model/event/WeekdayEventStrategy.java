package christmas.model.event;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class WeekdayEventStrategy extends EventDetailsHandler<WeekdayEventStrategy> {
    private static final int WEEK_DAY_DISCOUNT = 2_023;

    private final LocalDate date;
    private final Integer quantity;

    private WeekdayEventStrategy(final Integer day, final Integer quantity) {
        super(EventType.WEEKDAY);
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.quantity = quantity;
    }

    public static WeekdayEventStrategy of(final Integer day, final Integer quantity) {
        return new WeekdayEventStrategy(day, quantity);
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
        return WEEK_DAY_DISCOUNT * this.quantity;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
