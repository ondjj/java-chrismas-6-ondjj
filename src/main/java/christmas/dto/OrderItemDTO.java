package christmas.dto;

import java.util.Map;

public class OrderItemDTO {
    private final Map<String, Integer> item;

    public OrderItemDTO(Map<String, Integer> item) {
        this.item = item;
    }

    public Map<String, Integer> getItem() {
        return item;
    }
}
