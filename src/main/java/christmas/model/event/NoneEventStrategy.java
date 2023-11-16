package christmas.model.event;

import static christmas.util.Constants.NONE;
import static christmas.util.Constants.ZERO;

import christmas.util.enums.EventType;
import java.util.HashMap;
import java.util.Map;

public class NoneEventStrategy extends BaseEventHandler<NoneEventStrategy> {

    private static final NoneEventStrategy INSTANCE = new NoneEventStrategy();

    private NoneEventStrategy() {
        super(EventType.NONE);
    }

    @Override
    public Integer itemDiscount() {
        return ZERO;
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
