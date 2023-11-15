package christmas.model.event;

import static christmas.util.Constants.LIMIT_RANGE;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;
import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DdayEventStrategy extends EventDetailsHandler<DdayEventStrategy> {
    private static final int D_DAY_DISCOUNT = 900;
    private static final int CHRISTMAS = 25;
    private static final int INCREMENT = 100;

    private final LocalDate date;
    private final Integer totalPrice;

    private DdayEventStrategy(final Integer day, final Integer totalPrice) {
        super(EventType.D_DAY);
        this.date = LocalDate.of(YEAR, MONTH, day);
        this.totalPrice = totalPrice;
    }

    public static DdayEventStrategy of(final Integer day, final Integer totalPrice) {
        return new DdayEventStrategy(day, totalPrice);
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
        Map<String, String> details = new LinkedHashMap<>();
        if (isEventDate()) {
            return createEventDetails(getEventType(), itemDiscount());
        }
        details.put(getEventType().getNone(), getEventType().getNone());
        return details;
    }

    private int calculateDiscount() {
        return D_DAY_DISCOUNT + (date.getDayOfMonth() * INCREMENT);
    }

    private boolean isEventDate() {
        return totalPrice >= LIMIT_RANGE && date.getDayOfMonth() <= CHRISTMAS;
    }
}
