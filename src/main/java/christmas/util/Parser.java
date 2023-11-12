package christmas.util;

import static christmas.util.Constants.ERROR;

import christmas.util.enums.ErrorMessage;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private static final String SPLIT_REGEX_COMMA = ",";
    private static final String SPLIT_REGEX_DASH = "-";
    private static final Integer DEFAULT_KEY_INDEX = 0;
    private static final Integer DEFAULT_VALUE_INDEX = 1;

    public static Map<String, Integer> parseString(String orderItem) {
        List<String> parseComa = Arrays.asList(orderItem.split(SPLIT_REGEX_COMMA));
        return parseDash(parseComa);
    }

    public static String decimalFormatter(Integer won) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(won);
    }

    private static Map<String, Integer> parseDash(final List<String> parseComa) {
        Map<String, Integer> uniqueData = new LinkedHashMap<>();

        for (String item : parseComa) {
            List<String> parseDash = Arrays.asList(item.split(SPLIT_REGEX_DASH));
            validUnique(uniqueData, parseDash);
            uniqueData.put(parseDash.get(DEFAULT_KEY_INDEX), Integer.parseInt(parseDash.get(DEFAULT_VALUE_INDEX)));
        }
        return uniqueData;
    }

    private static void validUnique(final Map<String, Integer> uniqueData, final List<String> parseDash) {
        if (uniqueData.containsKey(parseDash.get(0))) {
            throw new IllegalStateException(ERROR + ErrorMessage.INVALID_ORDER);
        }
    }
}