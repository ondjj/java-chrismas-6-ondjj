package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayEvent implements Event {
    private static final int WEEK_DAY_DISCOUNT = 2023;

    private final LocalDate date;
    private final Integer price;

    private WeekdayEvent(final Integer day, final Integer price) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.price = price;
    }

    public static WeekdayEvent of(final Integer day, final Integer price) {
        return new WeekdayEvent(day, price);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return price - WEEK_DAY_DISCOUNT;
        }
        return price;
    }


    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
