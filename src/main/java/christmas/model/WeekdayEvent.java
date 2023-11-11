package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayEvent implements Event {
    private static final int WEEK_DAY_DISCOUNT = 2023;

    private final LocalDate date;

    private WeekdayEvent(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static WeekdayEvent of(final Integer day) {
        return new WeekdayEvent(day);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return WEEK_DAY_DISCOUNT;
        }
        return ZERO;
    }


    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
