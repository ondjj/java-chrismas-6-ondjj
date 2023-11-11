package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendEvent implements Event {
    private static final int WEEK_END_DISCOUNT = 2023;

    private final LocalDate date;

    private WeekendEvent(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static WeekendEvent of(final Integer day) {
        return new WeekendEvent(day);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return WEEK_END_DISCOUNT;
        }
        return ZERO;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
