package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class DdayEventTest {

    private Integer startDay;
    private Integer endDay;
    private Integer price;
    private Event ddayEvent;
    private Event endDayEvent;

    @BeforeEach
    void setUp() {
        startDay = 1;
        endDay = 25;
        price = 13000;
    }

    @Test
    void 생성_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);
        assertThat(ddayEvent).isNotNull();
    }

    @Test
    void 할인_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);
        endDayEvent = DdayEvent.of(endDay, price);

        assertThat(ddayEvent.itemDiscount()).isEqualTo(1000);
        assertThat(endDayEvent.itemDiscount()).isEqualTo(3400);
    }

    @DisplayName("D-Day에 따라 할인이 적용됩니다.")
    @Test
    void 이벤트_기간_테스트() {
        ddayEvent = DdayEvent.of(startDay, price);
        endDayEvent = DdayEvent.of(endDay, price);
        Event overDayEvent = DdayEvent.of(endDay + 1, price);

        assertThat(ddayEvent.itemDiscount()).isEqualTo(1000);
        assertThat(endDayEvent.itemDiscount()).isEqualTo(3400);
        assertThat(overDayEvent.itemDiscount()).isEqualTo(0);
    }
}
