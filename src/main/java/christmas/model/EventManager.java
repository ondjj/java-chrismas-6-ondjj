package christmas.model;

import static christmas.util.Constants.*;

import java.util.Map;

public class EventManager {
    private static final Integer EVENT_RANGE = 10000;
    private static final String WON = "원";
    private static final String COLON = ": ";

    private final EventGroupFacade eventGroupFacade;

    private EventManager(EventGroupFacade eventGroupFacade) {
        this.eventGroupFacade = eventGroupFacade;
    }

    public static EventManager of(EventGroupFacade eventGroupFacade) {
        return new EventManager(eventGroupFacade);
    }

    public StringBuilder getEventDetails() {
        StringBuilder eventDetails = new StringBuilder();
        if (!isEventRange()) {
            return eventDetails.append(NONE + LINE);
        }
        addEventDetails(eventDetails, eventGroupFacade.dDay());
        addEventDetails(eventDetails, eventGroupFacade.weekday());
        addEventDetails(eventDetails, eventGroupFacade.weekend());
        addEventDetails(eventDetails, eventGroupFacade.special());
        addEventDetails(eventDetails, eventGroupFacade.present());
        return eventDetails;
    }

    public Integer actualBenefit() {
        return eventGroupFacade.expectBenefit();
    }

    public Integer totalBenefit() {
        return eventGroupFacade.totalBenefit();
    }

    public String gift() {
        return eventGroupFacade.giftContent();
    }

    private boolean isEventRange() {
        return eventGroupFacade.getOrderBeforeTotalPrice() >= EVENT_RANGE;
    }

    private void addEventDetails(StringBuilder eventDetails, Map<String, String> details) {
        String eventName = validEventName(details);
        String sale = validEventSale(details);
        if (!eventName.equals(NONE)) {
            eventDetails.append(eventName)
                        .append(COLON)
                        .append(sale)
                        .append(WON)
                        .append(LINE);
        }
    }

    private String validEventName(Map<String, String> eventDetails) {
        return eventDetails.keySet().iterator().next();
    }

    private String validEventSale(Map<String, String> eventDetails) {
        return eventDetails.values().iterator().next();
    }
}
