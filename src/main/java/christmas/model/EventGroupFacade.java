package christmas.model;

import java.util.Map;

public class EventGroupFacade {
    private final EventGroup eventGroup;

    private EventGroupFacade(EventGroup eventGroup) {
        this.eventGroup = eventGroup;
    }

    public static EventGroupFacade of(final EventGroup eventGroup) {
        return new EventGroupFacade(eventGroup);
    }

    public Integer getOrderBeforeTotalPrice() {
        return eventGroup.getOrderBeforeTotalPrice();
    }

    public Integer expectBenefit() {
        return eventGroup.expectBenefit();
    }

    public Integer totalBenefit() {
        return eventGroup.totalBenefit();
    }

    public String giftContent() {
        return eventGroup.giftContent();
    }

    public Map<String, String> present() {
        return eventGroup.present();
    }

    public Map<String, String> dDay() {
        return eventGroup.dDay();
    }

    public Map<String, String> special() {
        return eventGroup.special();
    }

    public Map<String, String> weekday() {
        return eventGroup.weekday();
    }

    public Map<String, String> weekend() {
        return eventGroup.weekend();
    }
}
