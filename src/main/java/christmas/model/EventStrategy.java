package christmas.model;


import christmas.util.enums.EventType;
import java.util.Map;

public interface EventStrategy {
    Integer itemDiscount();
    EventType getEventType();
    Map<String, String> extractEventDetails();
}
