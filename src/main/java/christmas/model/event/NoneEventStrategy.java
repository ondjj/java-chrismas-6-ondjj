package christmas.model.event;

import static christmas.util.Constants.*;

import christmas.util.enums.EventType;
import java.util.HashMap;
import java.util.Map;

public class NoneEventStrategy implements EventStrategy {

    private static final NoneEventStrategy INSTANCE = new NoneEventStrategy();

    private NoneEventStrategy() {
    }

    @Override
    public Integer itemDiscount() {
        return ZERO;
    }

    @Override
    public EventType getEventType() {
        return EventType.NONE;
    }

    @Override
    public Map<String, String> createEventDetails(final EventType eventType, final Integer discount) {
        Map<String, String> noneEvent = new HashMap<>();
        noneEvent.put(NONE, NONE);
        return noneEvent;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        Map<String, String> noneEvent = new HashMap<>();
        noneEvent.put(NONE, NONE);
        return noneEvent;
    }

    public static NoneEventStrategy getInstance() {
        return INSTANCE;
    }
}
