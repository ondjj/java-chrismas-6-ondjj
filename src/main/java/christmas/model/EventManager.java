package christmas.model;

import java.util.Map;

public class EventManager {
    private static final Integer EVENT_RANGE = 10000;

    private final EventGroup eventGroup;

    private EventManager(EventGroup eventGroup) {
        this.eventGroup = eventGroup;
    }

    public static EventManager of(EventGroup eventGroup) {
        return new EventManager(eventGroup);
    }

    public StringBuilder getEventDetails() {
        StringBuilder eventDetails = new StringBuilder();
        if (!isEventRange()){
            return eventDetails.append("없음");
        }
        addEventDetails(eventDetails, dDay());
        addEventDetails(eventDetails, weekday());
        addEventDetails(eventDetails, weekend());
        addEventDetails(eventDetails, special());
        addEventDetails(eventDetails, present());
        return eventDetails;
    }

    public Integer actualBenefit() {
        return eventGroup.actualBenefit();
    }

    public Integer totalBenefit() {
        return eventGroup.totalBenefit();
    }

    public String gift() {
        return eventGroup.giftContent();
    }

    private Map<String, String> present() {
        return eventGroup.present();
    }

    private Map<String,String> dDay() {
        return eventGroup.dDay();
    }

    private Map<String, String> special() {
        return eventGroup.special();
    }

    private Map<String, String> weekday() {
        return eventGroup.weekday();
    }

    private Map<String, String> weekend() {
        return eventGroup.weekend();
    }

    private boolean isEventRange() {
        return eventGroup.getOrderBeforeTotalPrice() >= EVENT_RANGE;
    }

    private void addEventDetails(StringBuilder eventDetails, Map<String, String> details) {
        if (!validEventName(details).equals("없음")) {
            eventDetails.append(validEventName(details));
            eventDetails.append(": ");
            eventDetails.append(validEventSale(details));
            eventDetails.append("원");
            eventDetails.append("\n");
        }
    }

    private String validEventName(Map<String, String> eventDetails) {
        return eventDetails.keySet().iterator().next();
    }

    private String validEventSale(Map<String, String> eventDetails) {
        return eventDetails.values().iterator().next();
    }
}
