package christmas.model;


import christmas.util.enums.EventType;
import java.util.Map;

public interface Event {
    Integer itemDiscount();
    EventType getEventType();
    Map<String, String> extractEventDetails();
}
