package christmas.model;

import christmas.util.enums.MenuType;
import java.util.Map;

public class EventGroup {
    private final Order order;

    private DdayEvent ddayEvent;
    private WeekdayEvent weekdayEvent;
    private WeekendEvent weekendEvent;
    private SpecialEvent specialEvent;
    private PresentEvent presentEvent;

    public EventGroup(Order order) {
        this.order = order;
        initializeEvents();
    }

    public Integer getOrderBeforeTotalPrice() {
        return order.getBeforeTotalPrice();
    }

    public Integer actualBenefit() {
        return totalBenefit() - presentEvent.itemDiscount();
    }

    public Integer totalBenefit() {
        Integer total = 0;
        total += presentEvent.itemDiscount();
        total += weekendEvent.getWon();
        total += weekdayEvent.getWon();
        total += specialEvent.getWon();
        total += ddayEvent.calculatePrice();
        return total;
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
        ddayEvent = DdayEvent.of(order.getOrderDate());
        weekdayEvent = WeekdayEvent.of(order.getOrderDate(), order.findMenuCount(MenuType.DESSERT));
        weekendEvent = WeekendEvent.of(order.getOrderDate(), order.findMenuCount(MenuType.MAIN));
        specialEvent = SpecialEvent.of(order.getOrderDate());
        presentEvent = PresentEvent.of(order.getBeforeTotalPrice());
    }
}
