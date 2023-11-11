package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialEvent implements Event {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int CHRISTMAS = 25;

    private final LocalDate date;

    private SpecialEvent(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static SpecialEvent of(final Integer day) {
        return new SpecialEvent(day);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return SPECIAL_DISCOUNT;
        }
        return ZERO;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SUNDAY) || (date.getDayOfMonth() == CHRISTMAS);
    }
}
