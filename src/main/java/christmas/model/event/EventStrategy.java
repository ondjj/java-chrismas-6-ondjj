package christmas.model.event;


import christmas.util.enums.EventType;
import java.util.Map;

public interface EventStrategy {
    Integer itemDiscount();
    EventType getEventType();
    Map<String, String> createEventDetails(EventType eventType, Integer discount);
    Map<String, String> extractEventDetails();
}
