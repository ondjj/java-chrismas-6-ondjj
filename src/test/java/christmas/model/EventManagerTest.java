package christmas.model;

import static org.assertj.core.api.Assertions.*;

import christmas.util.enums.EventType;
import christmas.util.enums.MenuType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventManagerTest {

    Order order;
    EventGroup eventGroup;
    EventGroupFacade eventGroupFacade;
    EventManager eventManager;

    @BeforeEach
    void setUp() {
        VisitDate visitDate = VisitDate.of("3");
        OrderItem orderItem = OrderItem.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        order = Order.createOrder(visitDate, orderItem);
        eventGroup = initializeEvents(order);
        eventGroupFacade = EventGroupFacade.of(eventGroup);
        eventManager = EventManager.of(eventGroupFacade);
    }

    private EventGroup initializeEvents(Order order) {
        Map<EventType, EventStrategy> events = new HashMap<>();
        events.put(EventType.PRESENT, PresentEventStrategy.of(order.getBeforeTotalPrice()));
        events.put(EventType.D_DAY, DdayEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        events.put(EventType.WEEKDAY, WeekdayEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.DESSERT)));
        events.put(EventType.WEEKEND, WeekendEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.MAIN)));
        events.put(EventType.SPECIAL, SpecialEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        return EventGroup.of(order, events);
    }

    @Test
    void 이벤트_상세내역_테스트() {
        String content = "크리스마스 디데이 할인: -1,200원" + "\n"
                + "평일 할인: -4,046원" + "\n"
                + "특별 할인: -1,000원" + "\n"
                + "증정 이벤트: -25,000원" + "\n";
        assertThat(eventManager.getEventDetails().toString())
                .isEqualTo(content);
    }

    @Test
    void 실제_혜택_금액_테스트() {
        assertThat(eventManager.actualBenefit()).isEqualTo(6246);
    }

    @Test
    void 전체_혜택_금액_테스트() {
        assertThat(eventManager.totalBenefit()).isEqualTo(31246);
    }

    @Test
    void 증정품_확인_테스트() {
        assertThat(eventManager.gift()).isEqualTo("샴페인 1개");
    }
}