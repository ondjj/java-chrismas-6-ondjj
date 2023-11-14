package christmas.model;

import static christmas.util.Constants.ZERO;
import static christmas.util.validators.OrderItemValidator.validFormat;
import static christmas.util.validators.OrderItemValidator.validateOrderData;

import christmas.dto.OrderItemDTO;
import christmas.util.Parser;
import christmas.util.enums.Menu;
import christmas.util.enums.MenuType;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderItem {
    private final Map<String,Integer> item;

    private OrderItem(Map<String,Integer> data) {
        this.item = data;
    }

    public static OrderItem of(String item) {
        validFormat(item);
        Map<String, Integer> parseData = Parser.parseString(item);
        validateOrderData(parseData);
        return new OrderItem(parseData);
    }

    public OrderItemDTO toDTO() {
        return new OrderItemDTO(item);
    }

    public int getCount(String itemName) {
        return item.getOrDefault(itemName, ZERO);
    }

    public Map<MenuType, Integer> getItemQuantity() {
        Map<MenuType, Integer> menuCount = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : item.entrySet()) {
            String itemName = entry.getKey();
            Integer quantity = entry.getValue();
            Menu menu = Menu.getMenuByName(itemName);
            menuCount.put(menu.getType(), menuCount.getOrDefault(menu.getType(), ZERO) + quantity);
        }
        return menuCount;
    }

    public Integer totalPrice() {
        return item.entrySet().stream()
                .mapToInt(entry -> Menu.getMenuByName(entry.getKey()).getPrice() * entry.getValue())
                .sum();
    }
}
