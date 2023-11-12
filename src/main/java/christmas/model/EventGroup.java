package christmas.model;

import static christmas.util.Constants.ZERO;

import christmas.util.enums.MenuType;
import java.util.Map;
import java.util.stream.Stream;

public class EventGroup {
    private final Order order;

    private DdayEventStrategy ddayEvent;
    private WeekdayEventStrategy weekdayEvent;
    private WeekendEventStrategy weekendEvent;
    private SpecialEventStrategy specialEvent;
    private PresentEventStrategy presentEvent;

    private EventGroup(Order order) {
        this.order = order;
        initializeEvents();
    }

    public static EventGroup of(Order order) {
        return new EventGroup(order);
    }

    public Integer getOrderBeforeTotalPrice() {
        return order.getBeforeTotalPrice();
    }

    public Integer actualBenefit() {
        return totalBenefit() - presentEvent.itemDiscount();
    }

    public Integer totalBenefit() {
        return Stream.of(
                presentEvent.itemDiscount(),
                specialEvent.itemDiscount(),
                weekdayEvent.calculateDiscount(),
                weekendEvent.calculateDiscount(),
                ddayEvent.calculateDiscount()
        ).reduce(ZERO, Integer::sum);
    }

    public String giftContent() {
        return presentEvent.getPresent();
    }

    public Map<String, String> present() {
        return presentEvent.extractEventDetails();
    }

    public Map<String, String> dDay() {
        return ddayEvent.extractEventDetails();
    }

    public Map<String, String> special() {
        return specialEvent.extractEventDetails();
    }

    public Map<String, String> weekday() {
        return weekdayEvent.extractEventDetails();
    }

    public Map<String, String> weekend() {
        return weekendEvent.extractEventDetails();
    }

    private void initializeEvents() {
        ddayEvent = DdayEventStrategy.of(order.getOrderDate());
        weekdayEvent = WeekdayEventStrategy.of(order.getOrderDate(), order.findMenuCount(MenuType.DESSERT));
        weekendEvent = WeekendEventStrategy.of(order.getOrderDate(), order.findMenuCount(MenuType.MAIN));
        specialEvent = SpecialEventStrategy.of(order.getOrderDate());
        presentEvent = PresentEventStrategy.of(order.getBeforeTotalPrice());
    }
}
