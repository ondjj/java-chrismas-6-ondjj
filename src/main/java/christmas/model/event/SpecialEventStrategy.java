package christmas.model.event;

import static christmas.util.Constants.LIMIT_RANGE;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class SpecialEventStrategy extends EventDetailsHandler<SpecialEventStrategy> {
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int CHRISTMAS = 25;

    private final LocalDate date;
    private final Integer totalPrice;

    private SpecialEventStrategy(final Integer day, final Integer totalPrice) {
        super(EventType.SPECIAL);
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.totalPrice = totalPrice;
    }

    public static SpecialEventStrategy of(final Integer day, final Integer totalPrice) {
        return new SpecialEventStrategy(day, totalPrice);
    }

    @Override
    public Integer itemDiscount() {
        if (isEventDate()) {
            return SPECIAL_DISCOUNT;
        }
        return ZERO;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        return createEventDetails(getEventType(), itemDiscount());
    }

    private boolean isEventDate() {
        return isTotalPriceInRange() && isSpecialDate();
    }

    private boolean isTotalPriceInRange() {
        return totalPrice >= LIMIT_RANGE;
    }

    private boolean isSpecialDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || date.getDayOfMonth() == CHRISTMAS;
    }
}
