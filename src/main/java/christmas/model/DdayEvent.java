package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DdayEvent implements Event {
    private static final int D_DAY_DISCOUNT = 900;
    private static final int CHRISTMAS = 25;
    private static final int INCREMENT = 100;

    private final LocalDate date;

    private DdayEvent(final Integer day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static DdayEvent of(final Integer day) {
        return new DdayEvent(day);
    }

    @Override
    public Integer itemDiscount() {
        return calculatePrice();
    }

    @Override
    public EventType getEventType() {
        return EventType.D_DAY;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> details = new LinkedHashMap<>();
        if (isEventDate()) {
            return getDetailEvent(details);
        }
        details.put(getEventType().getNone(), getEventType().getNone());
        return details;
    }

    private Map<String, String> getDetailEvent(final Map<String, String> details) {
        details.put(getEventType().getDescription(), DASH + Parser.decimalFormatter(itemDiscount()));
        return details;
    }

    public int calculatePrice() {
        if (isEventDate()) {
            return D_DAY_DISCOUNT + (date.getDayOfMonth() * INCREMENT);
        }
        return 0;
    }

    private boolean isEventDate() {
        return date.getDayOfMonth() <= CHRISTMAS;
    }
}
