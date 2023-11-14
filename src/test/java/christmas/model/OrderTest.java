package christmas.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import christmas.util.enums.MenuType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    VisitDate visitDate;
    OrderItem orderItem;
    Order order;

    @BeforeEach
    void setUp() {
        visitDate = VisitDate.of("6");
        orderItem = OrderItem.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        order = Order.createOrder(visitDate, orderItem);
    }

    @Test
    void 전체_주문_금액_조회_테스트() {
        assertThat(order.getBeforeTotalPrice()).isEqualTo(orderItem.totalPrice());
        assertThat(order.getBeforeTotalPrice()).isEqualTo(142000);
    }

    @Test
    void 주문_일자_조회_테스트() {
        assertThat(order.getOrderDate()).isEqualTo(visitDate.getDate());
        assertThat(order.getOrderDate()).isEqualTo(6);
    }

    @Test
    void 전체_주문_개수_테스트() {
        assertThat(order.findMenuCount(MenuType.APPETIZER)).isEqualTo(0);
        assertThat(order.findMenuCount(MenuType.MAIN)).isEqualTo(2);
        assertThat(order.findMenuCount(MenuType.DESSERT)).isEqualTo(2);
        assertThat(order.findMenuCount(MenuType.DRINK)).isEqualTo(1);
    }
}