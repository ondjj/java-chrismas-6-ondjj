package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DdayEventTest {

    private Integer startDay;
    private Integer endDay;
    private Integer price;
    private EventCalendar ddayEvent;
    private EventCalendar endDayEvent;
    private EventCalendar overDayEvent;

    @BeforeEach
    void setUp() {
        startDay = 1;
        endDay = 25;
        price = 13000;
    }

    @Test
    void 생성_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);

        Assertions.assertThat(ddayEvent).isNotNull();
    }

    @Test
    void 할인_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);
        endDayEvent = DdayEvent.of(endDay, price);

        Assertions.assertThat(ddayEvent.itemDiscount()).isEqualTo(12000);
        Assertions.assertThat(endDayEvent.itemDiscount()).isEqualTo(9600);
    }

    @Test
    void 이벤트_기간_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);
        endDayEvent = DdayEvent.of(endDay, price);
        overDayEvent = DdayEvent.of(endDay + 1, price);

        Assertions.assertThat(ddayEvent.isEventDate()).isTrue();
        Assertions.assertThat(endDayEvent.isEventDate()).isTrue();
        Assertions.assertThat(overDayEvent.isEventDate()).isFalse();
    }
}
