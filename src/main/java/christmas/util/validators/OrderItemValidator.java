package christmas.util.validators;

import static christmas.util.Constants.ERROR;

import christmas.util.enums.ErrorMessage;
import christmas.util.enums.Menu;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderItemValidator {
    private static final int MAX_ORDER_MENU_COUNT = 20;
    private static final String PATTERN_STRING = "^[가-힣]*-\\d+(,[가-힣]*-\\d+)*$";
    private static final Pattern PATTERN = Pattern.compile(PATTERN_STRING);

    public static void validFormat(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR + ErrorMessage.INVALID_ORDER);
        }
    }

    public static void validateOrderData(Map<String, Integer> parseData) {
        containsValidMenuItems(parseData);
        validateOrderMenuCount(parseData);
    }

    private static void containsValidMenuItems(Map<String, Integer> parseData) {
        for (Map.Entry<String, Integer> entry : parseData.entrySet()) {
            if (!Menu.isValidMenu(entry.getKey())){
                throw new IllegalStateException(ERROR + ErrorMessage.INVALID_ORDER);
            }
        }
    }

    private static void validateOrderMenuCount(Map<String, Integer> parseData) {
        Integer menuCount = 0;
        for (Map.Entry<String, Integer> entry : parseData.entrySet()) {
            menuCount += entry.getValue();
        }
        validateCount(menuCount > MAX_ORDER_MENU_COUNT);
    }

    private static void validateCount(final boolean menuCount) {
        if (menuCount) {
            throw new IllegalStateException(ERROR + ErrorMessage.INVALID_ORDER);
        }
    }
}
