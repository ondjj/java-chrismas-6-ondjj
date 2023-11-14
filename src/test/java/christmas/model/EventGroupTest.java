package christmas.model;

import static org.assertj.core.api.Assertions.*;

import christmas.util.enums.EventType;
import christmas.util.enums.MenuType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventGroupTest {

    Order order;
    Map<EventType, EventStrategy> events;
    EventGroup eventGroup;

    @BeforeEach
    void setUp() {
        VisitDate visitDate = VisitDate.of("3");
        OrderItem orderItem = OrderItem.of("타파스-1,제로콜라-1");
        order = Order.createOrder(visitDate, orderItem);
        events = new HashMap<>();

        events.put(EventType.NONE, NoneEventStrategy.getInstance());
        events.put(EventType.PRESENT, PresentEventStrategy.of(order.getBeforeTotalPrice()));
        events.put(EventType.D_DAY, DdayEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        events.put(EventType.WEEKDAY, WeekdayEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.DESSERT)));
        events.put(EventType.WEEKEND, WeekendEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.MAIN)));
        events.put(EventType.SPECIAL, SpecialEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        eventGroup = EventGroup.of(order, events);
    }

    @Test
    void 주문_금액_확인_테스트() {
        assertThat(eventGroup.getOrderBeforeTotalPrice()).isEqualTo(order.getBeforeTotalPrice());
    }

    @Test
    void 총_혜택_금액_테스트() {
        assertThat(eventGroup.totalBenefit()).isEqualTo(0);
    }

    @Test
    void 예상_혜택_금액_테스트() {
        assertThat(eventGroup.expectBenefit()).isEqualTo(0);
    }

    @Test
    void 증정_여부_테스트() {
        assertThat(eventGroup.giftContent()).isEqualTo("없음");
    }
}