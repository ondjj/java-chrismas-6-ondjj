package christmas.model;

import christmas.util.enums.EventType;
import java.util.Map;

public class NoneEventStrategy implements EventStrategy {

    private static final NoneEventStrategy INSTANCE = new NoneEventStrategy();

    private NoneEventStrategy() {
    }

    @Override
    public Integer itemDiscount() {
        return null;
    }

    @Override
    public EventType getEventType() {
        return null;
    }

    @Override
    public Map<String, String> extractEventDetails() {
        return null;
    }

    public static NoneEventStrategy getInstance() {
        return INSTANCE;
    }
}
