package christmas.util.validators;


import christmas.util.enums.Menu;
import christmas.util.enums.MenuType;
import christmas.util.exception.IllegalArgumentExceptionType;
import christmas.util.exception.IllegalStateExceptionType;
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
            throw IllegalArgumentExceptionType.NUMBER_FORMAT_EXCEPTION.getException();
        }
    }

    public static void validateOrderData(Map<String, Integer> parseData) {
        containsValidMenuItems(parseData);
        validateOrderMenuCount(parseData);
        validateDrinkOrder(parseData);
    }

    private static void containsValidMenuItems(Map<String, Integer> parseData) {
        for (Map.Entry<String, Integer> entry : parseData.entrySet()) {
            if (!Menu.isValidMenu(entry.getKey())){
                throw IllegalStateExceptionType.DUPLICATION_MENU_NAME.getException();
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
            throw IllegalStateExceptionType.INVALID_ORDER_COUNT.getException();
        }
    }

    private static void validateDrinkOrder(final Map<String, Integer> item) {
        if (item.keySet().stream()
                .allMatch(menuName -> Menu.getMenuByName(menuName).getType() == MenuType.DRINK)) {
            throw IllegalStateExceptionType.INVALID_ORDER_ONLY_DRINK.getException();
        }
    }
}
