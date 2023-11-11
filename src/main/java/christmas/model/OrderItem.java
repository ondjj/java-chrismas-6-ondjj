package christmas.model;

import static christmas.util.validators.OrderItemValidator.containsValidMenuItems;
import static christmas.util.validators.OrderItemValidator.validFormat;

import christmas.util.Parser;
import java.util.Map;

public class OrderItem {
    private final Map<String,Integer> item;

    private OrderItem(Map<String,Integer> data) {
        this.item = data;
    }

    public static OrderItem of(String date) {
        validFormat(date);
        Map<String,Integer> parseData = Parser.parseString(date);
        containsValidMenuItems(parseData);
        return new OrderItem(parseData);
    }

    public int getCount(String itemName) {
        return item.getOrDefault(itemName, 0);
    }
}
