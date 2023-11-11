package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import java.time.LocalDate;

public class DdayEvent implements EventCalendar {
    private static final int D_DAY_DISCOUNT = 900;
    private static final int CHRISTMAS = 25;
    private static final int INCREMENT = 100;

    private final LocalDate date;
    private final Integer price;

    private DdayEvent(final Integer day, final Integer price) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.price = price;
    }

    public static DdayEvent of(final Integer day, final Integer price) {
        return new DdayEvent(day, price);
    }

    @Override
    public Integer itemDiscount() {
        return price - (D_DAY_DISCOUNT + (date.getDayOfMonth() * INCREMENT));
    }

    @Override
    public boolean isEventDate() {
        return date.getDayOfMonth() <= CHRISTMAS;
    }
}
