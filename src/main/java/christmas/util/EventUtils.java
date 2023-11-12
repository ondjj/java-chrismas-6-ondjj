package christmas.util;

import static christmas.util.Constants.*;

import christmas.util.enums.EventType;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventUtils {
    public static Map<String, String> createEventDetails(EventType eventType, Integer discount) {
        Map<String, String> details = new LinkedHashMap<>();
        if (isValid(discount)) {
            putDetails(eventType, discount, details);
            return details;
        }
        putNone(eventType, details);
        return details;
    }

    private static boolean isValid(final Integer discount) {
        return discount > ZERO;
    }

    private static void putNone(final EventType eventType, final Map<String, String> details) {
        details.put(eventType.getNone(), eventType.getNone());
    }

    private static void putDetails(final EventType eventType, final Integer discount,
                                   final Map<String, String> details) {
        details.put(eventType.getDescription(), DASH + Parser.decimalFormatter(discount));
    }
}
