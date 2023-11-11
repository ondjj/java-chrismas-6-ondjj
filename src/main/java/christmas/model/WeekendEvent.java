package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendEvent implements Event {
    private static final int WEEK_END_DISCOUNT = 2023;

    private final LocalDate date;
    private final Integer price;

    private WeekendEvent(final Integer day, final Integer price) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.price = price;
    }

    public static WeekendEvent of(final Integer day, final Integer price) {
        return new WeekendEvent(day, price);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return price - WEEK_END_DISCOUNT;
        }
        return price;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
