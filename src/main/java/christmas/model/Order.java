package christmas.model;


import static christmas.util.Constants.ZERO;

import christmas.util.enums.MenuType;
import java.util.Map;

public class Order {
    private final VisitDate visitDate;
    private final OrderItem orderItem;

    private Order(VisitDate visitDate, OrderItem orderItem) {
        this.visitDate = visitDate;
        this.orderItem = orderItem;
    }

    public static Order createOrder(VisitDate visitDate, OrderItem orderItem) {
        return new Order(visitDate, orderItem);
    }

    public Integer getBeforeTotalPrice() {
        return orderItem.totalPrice();
    }

    public Integer getOrderDate() {
        return visitDate.getDate();
    }

    public Integer findMenuCount(MenuType menuType) {
        Map<MenuType, Integer> menuCounts = orderItem.getItemQuantity();
        return menuCounts.getOrDefault(menuType, ZERO);
    }
}
