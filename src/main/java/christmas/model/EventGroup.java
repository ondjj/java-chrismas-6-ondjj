package christmas.model;

import static christmas.util.Constants.ZERO;

import christmas.model.event.DdayEventStrategy;
import christmas.model.event.EventStrategy;
import christmas.model.event.NoneEventStrategy;
import christmas.model.event.PresentEventStrategy;
import christmas.model.event.SpecialEventStrategy;
import christmas.model.event.WeekdayEventStrategy;
import christmas.model.event.WeekendEventStrategy;
import christmas.util.enums.EventType;
import java.util.Map;

public class EventGroup {
    private final Order order;
    private final Map<EventType, EventStrategy> events;

    private EventGroup(Order order, Map<EventType, EventStrategy> events) {
        this.order = order;
        this.events = events;
    }

    public static EventGroup of(Order order, Map<EventType, EventStrategy> events) {
        return new EventGroup(order, events);
    }

    public Integer getOrderBeforeTotalPrice() {
        return order.getBeforeTotalPrice();
    }

    public Integer expectBenefit() {
        return totalBenefit() - presentEvent().itemDiscount();
    }

    public Integer totalBenefit() {
        return events.values().stream()
                .map(EventStrategy::itemDiscount)
                .reduce(ZERO, Integer::sum);
    }

    public String giftContent() {
        return presentEvent().getPresent();
    }

    public Map<String, String> present() {
        return presentEvent().extractEventDetails();
    }

    public Map<String, String> dDay() {
        return ddayEvent().extractEventDetails();
    }

    public Map<String, String> special() {
        return specialEvent().extractEventDetails();
    }

    public Map<String, String> weekday() {
        return weekdayEvent().extractEventDetails();
    }

    public Map<String, String> weekend() {
        return weekendEvent().extractEventDetails();
    }

    private PresentEventStrategy presentEvent() {
        return (PresentEventStrategy) events.getOrDefault(EventType.PRESENT, NoneEventStrategy.getInstance());
    }

    private DdayEventStrategy ddayEvent() {
        return (DdayEventStrategy) events.getOrDefault(EventType.D_DAY, NoneEventStrategy.getInstance());
    }

    private SpecialEventStrategy specialEvent() {
        return (SpecialEventStrategy) events.getOrDefault(EventType.SPECIAL, NoneEventStrategy.getInstance());
    }

    private WeekdayEventStrategy weekdayEvent() {
        return (WeekdayEventStrategy) events.getOrDefault(EventType.WEEKDAY, NoneEventStrategy.getInstance());
    }

    private WeekendEventStrategy weekendEvent() {
        return (WeekendEventStrategy) events.getOrDefault(EventType.WEEKEND, NoneEventStrategy.getInstance());
    }
}
