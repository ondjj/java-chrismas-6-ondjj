package christmas.model;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.MONTH;
import static christmas.util.Constants.YEAR;

import christmas.util.Parser;
import christmas.util.enums.EventType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

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
        return SPECIAL_DISCOUNT;
    }

    @Override
    public EventType getEventType() {
        return EventType.SPECIAL;
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

    public Integer getWon() {
        if (isEventDate()) {
            return itemDiscount();
        }
        return 0;
    }

    private Map<String,String> getDetailEvent(final Map<String, String> details) {
        details.put(getEventType().getDescription(), DASH + Parser.decimalFormatter(itemDiscount()));
        return details;
    }

    private boolean isEventDate() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SUNDAY) || (date.getDayOfMonth() == CHRISTMAS);
    }
}
