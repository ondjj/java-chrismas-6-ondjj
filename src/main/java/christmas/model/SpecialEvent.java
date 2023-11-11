package christmas.model;

import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialEvent implements Event {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int CHRISTMAS = 25;

    private final LocalDate date;
    private final Integer price;

    private SpecialEvent(final Integer day, final Integer price) {
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.price = price;
    }

    public static SpecialEvent of(final Integer day, final Integer price) {
        return new SpecialEvent(day, price);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return price - SPECIAL_DISCOUNT;
        }
        return price;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SUNDAY) || (date.getDayOfMonth() == CHRISTMAS);
    }
}
